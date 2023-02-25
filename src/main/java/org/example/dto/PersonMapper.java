package org.example.dto;

import java.util.List;

import org.example.entity.Person;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface PersonMapper {

    PersonDto mapFromEntity(Person person);

    @Mapping(target = "adult", ignore = true)
    Person mapFromDto(PersonDto dto);

    List<PersonDto> mapListFromEntity(List<Person> entityList);

}
