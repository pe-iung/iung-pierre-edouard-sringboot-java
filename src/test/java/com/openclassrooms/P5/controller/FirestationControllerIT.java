package com.openclassrooms.P5.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.P5.dto.firestation.post.AddFirestationRequest;
import com.openclassrooms.P5.model.Firestation;
import com.openclassrooms.P5.repository.FirestationRepository;
import com.openclassrooms.P5.repository.FirestationRepositoryFromJson;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class FirestationControllerIT {

    @Autowired
    private FirestationRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private FirestationRepositoryFromJson firestationRepositoryFromJson;

    @Test
    public void testAddFirestation() throws Exception {
        // given a firestation
        
        final int stationNumber = 99;
        final String stationAddress = "1270 route de la plage antibes";
        
        final AddFirestationRequest addFirestationRequest = new AddFirestationRequest(stationAddress,stationNumber);
        final Firestation expectedResult = new Firestation(stationAddress, stationNumber);

        // when Perform POST request to add firesation
        final ResultActions response = mockMvc.perform(post("/firestations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addFirestationRequest)));
        
        
        // then
        response.andExpect(status().isCreated())  // Verify status is OK
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))  // Verify content type is JSON
                .andExpect(jsonPath("$.address").value(stationAddress))  // Verify response data
                .andExpect(jsonPath("$.station").value(stationNumber));

        final List<Firestation> savedResponse = repository.getFirestationsByStation(stationNumber);
        Assertions.assertThat(savedResponse)
                .hasSize(1)
                .contains(expectedResult);

        // Then the service is called with the correct arguments
        //verify(firestationService).saveFirestation(firestation);
    }

    @Test
    public void testDeleteFirestation() throws Exception {

        //given a station number
        final String stationAddress = "908 73rd St";
        List<Firestation> existingFirestation = firestationRepositoryFromJson.getFirestations()
                .stream()
                .filter(f->f.getAddress().equals(stationAddress))
                .toList();
        Assertions.assertThat(existingFirestation)
                .hasSize(1);

        // when Perform POST request to add firesation
                final ResultActions response = mockMvc
                        .perform(delete("/firestations/"+stationAddress));

        // then
        response.andExpect(status().isOk());

        List<Firestation> deletedFirestation = repository.getFirestations()
                .stream()
                .filter(f->f.getAddress().equals(stationAddress))
                .toList();
        Assertions.assertThat(deletedFirestation)
                .hasSize(0);

    }
}
