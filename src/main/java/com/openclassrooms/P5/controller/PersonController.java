package com.openclassrooms.P5.controller;

import com.openclassrooms.P5.dto.person.post.AddPersonRequest;
import com.openclassrooms.P5.dto.person.put.UpdatePersonRequest;
import com.openclassrooms.P5.model.Person;
import com.openclassrooms.P5.service.PersonServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class PersonController {

    private final PersonServiceImpl personServiceImpl;


    @PostMapping("/persons")
    public ResponseEntity<?> addPerson(@Validated @RequestBody AddPersonRequest addPersonRequest) {

        Person person = new Person(
                addPersonRequest.getFirstName(),
                addPersonRequest.getLastName(),
                addPersonRequest.getAddress(),
                addPersonRequest.getCity(),
                addPersonRequest.getZip(),
                addPersonRequest.getPhone(),
                addPersonRequest.getEmail()
        );

        Person savedPerson = personServiceImpl.savePerson(person);

        log.info("A new person was added");
        log.debug("addPersonRequest = {}", addPersonRequest);
        log.debug("saved Person = {}", savedPerson);

        return ResponseEntity.status(HttpStatus.OK).body(addPersonRequest);
    }

    @DeleteMapping("/person/{id}")
    public void deletePersonById(@PathVariable String id) {
        personServiceImpl.deletePersonById(id);
        log.info("A person was deleted with id = {}", id);
    }

    @PutMapping("/persons")
    public ResponseEntity<?> updatePerson(@Validated @RequestBody UpdatePersonRequest updatePersonRequest) {
        Person updatedPerson = new Person(
                updatePersonRequest.getFirstName(),
                updatePersonRequest.getLastName(),
                updatePersonRequest.getAddress(),
                updatePersonRequest.getCity(),
                updatePersonRequest.getZip(),
                updatePersonRequest.getPhone(),
                updatePersonRequest.getEmail());
        personServiceImpl.updatePerson(updatedPerson);

        log.info("a person was updated with firstname ={} and lastname = {}",
                updatePersonRequest.getFirstName(),
                updatePersonRequest.getLastName()
        );
        log.debug("this updatePersonRequest was received = {}", updatePersonRequest);

        return ResponseEntity.status(HttpStatus.OK).body(updatePersonRequest);


    }

}
