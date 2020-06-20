package de.oglimmer.lazydemo.service;

import de.oglimmer.lazydemo.dao.AddressRepository;
import de.oglimmer.lazydemo.dao.PersonRepository;
import de.oglimmer.lazydemo.dto.PersonDto;
import de.oglimmer.lazydemo.entity.Person;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {

    @NonNull
    private PersonRepository personRepository;
    @NonNull
    private AddressRepository addressRepository;

    private transient MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

    {
//        mapperFactory.classMap(PersonDto.class, Person.class)
//                .byDefault()
//                .register();
//        mapperFactory.classMap(Person.class, PersonDto.class)
//                .byDefault()
//                .register();
    }

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

    public List<PersonDto> getPersons() {
        final MapperFacade mapperFacade = mapperFactory.getMapperFacade();
        final List<Person> all = personRepository.findAll();
        System.out.println("LOADED LIST OF PERSONS");
        return mapperFacade.mapAsList(all, PersonDto.class);
    }
}
