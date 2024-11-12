package com.openclassrooms.P5.controller;

import com.openclassrooms.P5.dto.person.post.AddPersonRequest;
import com.openclassrooms.P5.dto.person.post.PersonAddedResponse;
import com.openclassrooms.P5.model.Person;
import com.openclassrooms.P5.service.PersonServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PersonController {

    private final PersonServiceImpl personServiceImpl;


    @PostMapping("/persons")
    public PersonAddedResponse addPerson(@Validated @RequestBody AddPersonRequest addPersonRequest) {

        Person person = new Person(
                addPersonRequest.getFirstName(),
                addPersonRequest.getLastName(),
                addPersonRequest.getAddress(),
                addPersonRequest.getCity(),
                addPersonRequest.getZip(),
                addPersonRequest.getPhone(),
                addPersonRequest.getEmail()
                );

        Person savedPerson =  personServiceImpl.savePerson(person);

        return new PersonAddedResponse(savedPerson);
    }

    @DeleteMapping("/person/{id}")
    public void deletePersonById(@PathVariable String id) {
        personServiceImpl.deletePersonById(id);
    }

}
