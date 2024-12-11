package com.openclassrooms.P5.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PhoneAlertIT {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testPhoneAlert() throws Exception {
        //given a firestation number

        int firestationNumberRequested = 2;

        //when a call is made to PersonInfoLastName endpoint
        final ResultActions response = mockMvc
                .perform(get("/phoneAlert?firestation=" + firestationNumberRequested)
                        .contentType(MediaType.APPLICATION_JSON));

        //then the response is expected
        response.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$", hasItem("841-874-7512")))
                .andExpect(jsonPath("$", hasItem("841-874-7878")))
                .andExpect(jsonPath("$", hasItem("841-874-7458")))
                .andExpect(jsonPath("$", hasItem("841-874-6513")));


    }
}
