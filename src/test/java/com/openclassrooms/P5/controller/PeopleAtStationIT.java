package com.openclassrooms.P5.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PeopleAtStationIT {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testPeopleAtStationAndCountAdultAndMinor() throws Exception {
        //given a station number
        int stationNumber = 2;
        //when a call is made to the endpoint to list people living near the station and count the adult/minor
        final ResultActions response = mockMvc.perform(get("/firestation?stationNumber=" + stationNumber).contentType(MediaType.APPLICATION_JSON));

        //then the response is expected
        response.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.personWithPhoneAndAddressLists", hasSize(5)))
                .andExpect(jsonPath("$.counterMajor").value(4))
                .andExpect(jsonPath("$.counterMinor").value(1));
    }
}
