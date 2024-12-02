package com.openclassrooms.P5.repository;

import com.openclassrooms.P5.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonRepository {

    List<Person> getPersonsByAddress(String address);

    List<Person> getPersonsByCity(String city);

    Optional<Person> findPersonById(String personId);

    List<Person> getPersons();

    void addPerson(Person person);

    void deletePersonById(String id);

}
