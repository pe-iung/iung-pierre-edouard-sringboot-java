package com.openclassrooms.P5.service;

import com.openclassrooms.P5.model.Person;
import com.openclassrooms.P5.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PersonServiceImpl implements PersonService{

    private final PersonRepository personRepository;

    public Person savePerson(Person person) {
        personRepository.addPerson(person);
        return person;
    }

    public void deletePersonById(String id) {
        personRepository.deletePersonById(id);
    }

}
