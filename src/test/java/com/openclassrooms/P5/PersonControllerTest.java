package com.openclassrooms.P5;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.P5.dto.person.post.AddPersonRequest;
import com.openclassrooms.P5.model.Firestation;
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

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerTest {

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
        final  String city = "NY";
        final  String zip = "9999";
        final  String phone = "01234567";
        final  String email = "john@doe.com";

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
        final List<Person> savedResponse = personRepository.getPersonByFirstnameAndLastname(firstName,lastName);
        Assertions.assertThat(savedResponse)
                .hasSize(1)
                .contains(expectedPerson);

    }
}
