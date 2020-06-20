package de.oglimmer.lazydemo;

import de.oglimmer.lazydemo.dto.PersonDto;
import de.oglimmer.lazydemo.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/person")
@AllArgsConstructor
public class PersonController {

    private PersonService personService;

    @PostMapping
    public long createPerson(@RequestBody PersonDto personDto) {
        personService.createPerson(personDto);
        return personDto.getPersonId();
    }

    @GetMapping
    public List<PersonDto> getPerson() {
        return personService.getPersons();
    }

}
