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
public class FloodAlertIT {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testFloodAlert() throws Exception {
        // given a station number
        int stationRequestedNumber = 3;

        // when a call is made to the flood alert endpoint
        final ResultActions response = mockMvc
                .perform(get("/flood/stations?stations=" + stationRequestedNumber)
                        .contentType(MediaType.APPLICATION_JSON));

        // then an expected response is answered
        response.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(5)));

    }
}
