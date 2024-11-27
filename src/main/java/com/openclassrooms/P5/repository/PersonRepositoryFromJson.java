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

    @Override
    public List<Person> getPersonsByAddress(String address) {
        return this.getPersons()
                .stream()
                .filter(f -> f.getAddress().equals(address))
                .toList();
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

    @Override
    public void addPerson(Person person) {
        dataLoader.addPerson(person);
    }

    /**
     *
     * @param id is the concatenation of "firstname" + "-" + "lastname"
     * @return true
     */
    @Override
    public void deletePersonById(String id) {
        dataLoader.deletePersonById(id);
    }
}