package de.oglimmer.lazydemo.service;

import de.oglimmer.lazydemo.dao.AddressRepository;
import de.oglimmer.lazydemo.dao.PersonRepository;
import de.oglimmer.lazydemo.dto.PersonDto;
import de.oglimmer.lazydemo.entity.Person;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

import static javax.transaction.Transactional.TxType.NEVER;

@Service
@RequiredArgsConstructor
public class PersonService {

    @NonNull
    private PersonRepository personRepository;
    @NonNull
    private AddressRepository addressRepository;

    @NonNull
    @Resource
    private PlatformTransactionManager transactionManager;

    @NonNull
    @PersistenceContext
    private EntityManager entityManager;

    private MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

    public void createPerson(PersonDto personDto) {
        final MapperFacade mapperFacade = mapperFactory.getMapperFacade();
        final Person entity = mapperFacade.map(personDto, Person.class);
        Person newEntity = personRepository.save(entity);
        entity.getAddresses().forEach(e -> {
            e.setPerson(entity);
            addressRepository.save(e);
        });
        mapperFacade.map(newEntity, personDto);
    }

    @SneakyThrows
    @Transactional(NEVER)
    public List<PersonDto> getPersons() {
        final MapperFacade mapperFacade = mapperFactory.getMapperFacade();
        final List<Person> all = personRepository.findAll();

        // detach all objects
//        entityManager.clear();

        // detach single objects..
        // entityManager.detach();

        // "disable" exceptions from lazy-loading
        // all.forEach(e -> {
        //     e.setAddresses(null);
        // });

        // Access to TransactionManager
        // System.out.println(TransactionSynchronizationManager.isActualTransactionActive());

        System.out.println("LOADED LIST OF PERSONS");

        // this fails with:
        // org.hibernate.LazyInitializationException: failed to lazily initialize a collection of role: de.oglimmer.lazydemo.entity.Person.addresses, could not initialize proxy - no Session
        return mapperFacade.mapAsList(all, PersonDto.class);
    }
}
