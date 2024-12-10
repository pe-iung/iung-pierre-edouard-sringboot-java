package com.openclassrooms.P5.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.P5.dto.person.post.AddPersonRequest;
import com.openclassrooms.P5.dto.person.put.UpdatePersonRequest;
import com.openclassrooms.P5.model.Person;
import com.openclassrooms.P5.repository.PersonRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonIT {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAddPerson() throws Exception {

        // Given a Person
        final String firstName = "John";
        final String lastName = "Doe";
        final String address = "Time square, New York city";
        final String city = "NY";
        final String zip = "9999";
        final String phone = "01234567";
        final String email = "john@doe.com";

        final AddPersonRequest addPersonRequest = new AddPersonRequest(
                firstName,
                lastName,
                address,
                city,
                zip,
                phone,
                email);

        final Person expectedPerson = new Person(
                firstName,
                lastName,
                address,
                city,
                zip,
                phone,
                email);

        // When performing HTTP POST request to add Person
        final ResultActions response = mockMvc.perform(post("/persons")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(addPersonRequest)));

        // Then the response a contains expected data and status is OK
        response.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value(firstName))
                .andExpect(jsonPath("$.lastName").value(lastName))
                .andExpect(jsonPath("$.address").value(address))
                .andExpect(jsonPath("$.city").value(city))
                .andExpect(jsonPath("$.zip").value(zip))
                .andExpect(jsonPath("$.phone").value(phone))
                .andExpect(jsonPath("$.email").value(email));

        // Then personRepository contains the newly added Person
        String personId = firstName + "-" + lastName;
        final Optional<Person> savedResponse = personRepository.findPersonById(personId);
        Assertions.assertThat(savedResponse)
                .isNotEmpty()
                .contains(expectedPerson);

    }

    @Test
    public void testUpdatePerson() throws Exception {

        // Given an existing Person
        final String firstName = "John2";
        final String lastName = "Doe2";
        final String address = "Time square, New York city";
        final String city = "NY";
        final String zip = "9999";
        final String phone = "01234567";
        final String email = "john@doe.com";
        Person existingPerson = new Person(
                firstName,
                lastName,
                address,
                city,
                zip,
                phone,
                email

        );
        personRepository.addPerson(existingPerson);

        // Given an updatePersonRequest
        final String existingFirstName = "John2";
        final String existingLastName = "Doe2";
        final String updatedAddress = "champ élisé";
        final String updatedCity = "Paris";
        final String updatedZip = "75008";
        final String updatedPhone = "012345678";
        final String updatedEmail = "johnUpdated@doe.com";


        // Given an updatePersonRequest
        UpdatePersonRequest updatePersonRequest = new UpdatePersonRequest(
                existingFirstName,
                existingLastName,
                updatedAddress,
                updatedCity,
                updatedZip,
                updatedPhone,
                updatedEmail
        );

        // Given an expectedUpdatedPerson
        final Person expectedUpdatedPerson = new Person(
                existingFirstName,
                existingLastName,
                updatedAddress,
                updatedCity,
                updatedZip,
                updatedPhone,
                updatedEmail);

        // When performing HTTP PUT request to update an existing Person
        final ResultActions response = mockMvc.perform(put("/persons")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatePersonRequest)));

        // Then the response a contains expected data and status is OK
        response.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value(existingFirstName))
                .andExpect(jsonPath("$.lastName").value(existingLastName))
                .andExpect(jsonPath("$.address").value(updatedAddress))
                .andExpect(jsonPath("$.city").value(updatedCity))
                .andExpect(jsonPath("$.zip").value(updatedZip))
                .andExpect(jsonPath("$.phone").value(updatedPhone))
                .andExpect(jsonPath("$.email").value(updatedEmail));

        // Then personRepository contains the newly added Person

        String personId = firstName + "-" + lastName;
        final Optional<Person> savedResponse = personRepository.findPersonById(personId);
        Assertions.assertThat(savedResponse)
                .isNotEmpty()
                .contains(expectedUpdatedPerson);

    }

    @Test
    public void testDeletePerson() throws Exception {

        // Given an existing Person
        final String firstName = "Johna";
        final String lastName = "Doea";
        final String address = "Time square, New York city";
        final String city = "NY";
        final String zip = "9999";
        final String phone = "01234567";
        final String email = "johna@doea.com";
        Person existingPerson = new Person(
                firstName,
                lastName,
                address,
                city,
                zip,
                phone,
                email

        );
        personRepository.addPerson(existingPerson);


        String personId = firstName+"-"+lastName;
        //we check the person exist before calling the delete http
        Assertions.assertThat(personRepository.findPersonById(personId))
                .contains(existingPerson);

        // When performing HTTP DELETE request to update an existing Person
        final ResultActions response = mockMvc.perform(delete("/person/"+personId)
                .contentType(MediaType.APPLICATION_JSON));

        //then the person has been deleted from the repository

        final Optional<Person> savedResponse = personRepository.findPersonById(personId);
        Assertions.assertThat(savedResponse)
                .isEmpty();

    }


}
