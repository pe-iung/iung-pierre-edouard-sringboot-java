package com.openclassrooms.P5.service;

import com.openclassrooms.P5.model.Person;

public interface PersonService {
    public Person savePerson(Person person);
    public void deletePersonById(String id);
}
