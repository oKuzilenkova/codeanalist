package org.example.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.example.dto.PersonDto;
import org.example.entity.Person;
import org.example.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

public class WorkerServiceImpl implements WorkerService {

    @Autowired
    private PersonRepository repository;

    @Autowired
    private PersonService service;

    @Scheduled(cron = "0 0 0 * * ?")
    public void setDocumentRequired() {
        List<PersonDto> personList = service.findAll();
        personList.forEach(dto -> {
            LocalDate localDate = LocalDate.now().minusYears(14);
            Date nYearsBefore = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            if (dto.getBirthDate().before(nYearsBefore)) {
                Person person = repository.findByPersonId(dto.getId());
                person.setDocumentRequired(true);
                repository.save(person);

                // Сохранение дефолтного документа, привязанного к person
                // createAndSaveDefaultDocument(dto.getId());
            }
        });
    }
}
