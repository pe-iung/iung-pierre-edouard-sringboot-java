package com.openclassrooms.P5.service;

import com.openclassrooms.P5.dto.person.Child;
import com.openclassrooms.P5.model.Person;

import java.util.List;

public interface PersonService {
    Person savePerson(Person person);
    void deletePersonById(String id);
    List<Child> childAlertByAddress(String address);
}
