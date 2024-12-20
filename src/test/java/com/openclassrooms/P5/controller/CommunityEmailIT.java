package com.openclassrooms.P5.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.P5.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CommunityEmailIT {


    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCommunityEmailExistingCity() throws Exception {

        // Given an existing repository Person
        String email = "jaboyd@email.com";

        List<String> expectedResponse = List.of(
                "jaboyd@email.com",
                "drk@email.com",
                "tenz@email.com",
                "jaboyd@email.com",
                "jaboyd@email.com",
                "drk@email.com",
                "tenz@email.com",
                "jaboyd@email.com",
                "jaboyd@email.com",
                "tcoop@ymail.com",
                "lily@email.com",
                "soph@email.com",
                "ward@email.com",
                "zarc@email.com",
                "reg@email.com",
                "jpeter@email.com",
                "jpeter@email.com",
                "aly@imail.com",
                "bstel@email.com",
                "ssanw@email.com",
                "bstel@email.com",
                "clivfd@ymail.com",
                "gramps@email.com");

        //when we call the communityEmail with a correct city
        final ResultActions response = mockMvc.perform(get("/communityEmail?city=Culver")
                .contentType(MediaType.APPLICATION_JSON));

        // Then the response a contains expected data and status is OK
        response.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(23)))
                .andExpect(jsonPath("$", hasItem("jaboyd@email.com")));


    }

}
