package com.openclassrooms.P5.repository;
import com.openclassrooms.P5.model.Person;

import java.util.List;
import java.util.Optional;

import com.openclassrooms.P5.model.Firestation;

import java.util.List;
import java.util.Optional;

public interface PersonRepository {

    Optional<Person> findPersonByAddress(String address);

    List<Person> getPersonByFirstnameAndLastname(String firstname, String lastname);

    List<Person> getPersons();

}
