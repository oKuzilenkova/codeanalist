package org.example.service;

import java.util.List;

import org.example.dto.PersonDto;
import org.example.entity.Person;

public interface PersonService {

    PersonDto findPersonById(Long personId);

    List<PersonDto> findAll();

    Person save(PersonDto dto);
}
