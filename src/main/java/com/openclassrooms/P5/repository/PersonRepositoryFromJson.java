package com.openclassrooms.P5.repository;

import com.openclassrooms.P5.model.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
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
    public List<Person> getPersonsByCity(String city) {
        return this.getPersons()
                .stream()
                .filter(f -> f.getCity().equals(city))
                .toList();
    }

    @Override
    public Optional<Person> findPersonById(String personId) {
        return this.getPersons()
                .stream()
                .filter(f -> (f.getId().equals(personId)))
                .findFirst();
    }

    @Override
    public List<Person> getPersons() {
        return this.dataLoader.getPersons();
    }

    @Override
    public void addPerson(Person person) {
        dataLoader.getPersons().add(person);
        log.info("New person added: {}", person);
    }

    /**
     *
     * @param id is the concatenation of "firstname" + "-" + "lastname"
     * @return void
     */
    @Override
    public void deletePersonById(String id) {
        List<Person> persons = dataLoader.getPersons();
        boolean removed = persons.removeIf(person -> person.getId().equals(id));
        if (removed) {
            log.info("Person removed with id= firstname-lastname: {}", id);
        } else {
            log.warn("No person found with id= firstname-lastname: {}", id);
        }
    }

}