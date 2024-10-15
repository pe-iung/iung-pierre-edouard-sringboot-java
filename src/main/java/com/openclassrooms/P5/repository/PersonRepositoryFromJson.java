package com.openclassrooms.P5.repository;

import com.openclassrooms.P5.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PersonRepositoryFromJson implements PersonRepository{

    private final DataLoader dataLoader;

    public Person getPersonByAddress(String address) {

        return findPersonByAddress(address).orElseThrow(() -> new RuntimeException("Not found"));
    }

    @Override
    public Optional<Person> findPersonByAddress(String address) {
        return this.getPersons()
                .stream()
                .filter(f -> f.getAddress().equals(address))
                .findFirst();
    }

    @Override
    public List<Person> getPersonByFirstnameAndLastname(String firstname, String lastname) {
        return this.getPersons()
                .stream()
                .filter(f -> (f.getFirstName().equals(firstname)))
                .filter(f -> (f.getLastName().equals(lastname)))
                .toList();
    }

    @Override
    public List<Person> getPersons() {
        return this.dataLoader.getPersons();
    }
}