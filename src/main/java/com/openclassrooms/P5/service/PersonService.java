package com.openclassrooms.P5.service;

import com.openclassrooms.P5.model.Firestation;
import com.openclassrooms.P5.model.Person;
import com.openclassrooms.P5.repository.PersonRepository;
import com.openclassrooms.P5.repository.PersonRepositoryFromJson;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PersonService {

    private final PersonRepository personRepository;

    public Person savePerson(Person person) {
        personRepository.addPerson(person);
        return person;
    }

    public void deletePersonById(String id) {
        personRepository.deletePersonById(id);
    }

}
