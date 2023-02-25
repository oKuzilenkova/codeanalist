package org.example.service;

import java.util.List;

import org.example.dto.PersonDto;
import org.example.dto.PersonMapper;
import org.example.entity.Person;
import org.example.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository repository;

    @Autowired
    private PersonMapper mapper;

    public PersonDto findPersonById(Long personId) {
        Person person = repository.findByPersonId(personId);
            final PersonDto personDto = mapper.mapFromEntity(person);
        return personDto;
    }

    public List<PersonDto> findAll() {
        List<Person> people = repository.findAll();
        return mapper.mapListFromEntity(people);
    }

    public Person save(PersonDto dto) {
        Person person = mapper.mapFromDto(dto);
        return repository.save(person);
    }
}
