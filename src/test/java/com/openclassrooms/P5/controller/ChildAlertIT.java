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

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ChildAlertIT {


    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testChildAlert() throws Exception {
        //given an address
        String addressRequested = "892 Downing Ct";
        String expectedFirstName = "Zach";
        String expectedLastName = "Zemicks";


        //when a call is mad to childAlert
        final ResultActions response = mockMvc
                .perform(get("/childAlert?address=" + addressRequested)
                        .contentType(MediaType.APPLICATION_JSON));


        //then the response content is expected
        response.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].firstName").value(expectedFirstName))
                .andExpect(jsonPath("$[0].lastName").value(expectedLastName));

    }
}
