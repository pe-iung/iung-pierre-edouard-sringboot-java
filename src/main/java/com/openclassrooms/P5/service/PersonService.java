package com.openclassrooms.P5.service;

import com.openclassrooms.P5.model.Firestation;
import com.openclassrooms.P5.model.Person;
import com.openclassrooms.P5.repository.PersonRepositoryFromJson;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PersonService {

    private final PersonRepositoryFromJson personRepositoryFromJson;

    public Person savePerson(Person person) {
        personRepositoryFromJson.addPerson(person);
        return person;
    }

    public void deletePersonById(String id) {
        personRepositoryFromJson.deletePersonById(id);
    }

}
