package org.example.controller;

import java.util.List;

import org.example.common.json.JsonItem;
import org.example.common.json.JsonItemFactory;
import org.example.dto.PersonDto;
import org.example.dto.PersonMapper;
import org.example.entity.Person;
import org.example.service.PersonService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/person")
@Tag(name = "API для ФЛ")
public class PersonController {

    private PersonService service;

    private PersonMapper mapper;

    /**
     * Просмотр информации о ФЛ.
     */
    @Operation(summary = "Просмотр ФЛ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ФЛ",
                    content = @Content(schema = @Schema(implementation = PersonDto.class))) })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonItem<PersonDto> findById(@PathVariable("id") Long personId) {
        final PersonDto dto = service.findPersonById(personId);
        return JsonItemFactory.successResult(dto);
    }

    /**
     * Получение полного списка ФЛ
     *
     * @return JsonItem<List < PersonDto>>
     */
    @Operation(summary = "Список ФЛ")
    @ApiResponse(responseCode = "200", description = "Список ФЛ",
            content = @Content(schema = @Schema(implementation = List.class)))
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonItem<List<PersonDto>> findAll() {
        return JsonItemFactory.successResult(service.findAll());
    }

    /**
     * Сохранение ФЛ
     *
     * @return JsonItem<PersonDto>
     */
    @Operation(summary = "Сохранение ФЛ", method = "POST")
    @ApiResponse(responseCode = "200", description = "Список ФЛ",
            content = @Content(schema = @Schema(implementation = PersonDto.class)))
    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonItem<PersonDto> save(@RequestBody PersonDto dto) {
        Person person = service.save(dto);
        return JsonItemFactory.successResult(mapper.mapFromEntity(person));
    }
}
