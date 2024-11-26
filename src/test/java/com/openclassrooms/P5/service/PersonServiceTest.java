package com.openclassrooms.P5.service;

import com.openclassrooms.P5.model.Person;
import com.openclassrooms.P5.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    PersonRepository personRepository;

    @InjectMocks
    PersonServiceImpl personService;

    @Test
    public void testSavePerson() {
        //given a Person
        Person personToTest = new Person(
                "Buzz",
                "eclair",
                "infinity road NYC",
                "New York",
                "99751",
                "30123456",
                "buzz@eclair.com"
        );

        // when we add a person from repository
        personService.savePerson(personToTest);

        //then the dataloader is called with proper arguments
        verify(personRepository, times(1)).addPerson(personToTest);



    }

    @Test
    public void testDeletePersonById() {
        //given an id
        String id = "mickey-mouse";

        // when we add a person from repository
        personService.deletePersonById(id);

        //then the dataloader is called with proper arguments
        verify(personRepository, times(1)).deletePersonById(id);


    }
}
