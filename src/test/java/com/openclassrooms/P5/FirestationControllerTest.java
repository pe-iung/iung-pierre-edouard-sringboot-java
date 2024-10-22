package com.openclassrooms.P5;

import com.openclassrooms.P5.controller.FirestationController;
import com.openclassrooms.P5.model.Firestation;
import com.openclassrooms.P5.service.FirestationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
public class FirestationControllerTest {

//    @Mock
//    private FirestationService firestationService;
//
//    @InjectMocks
//    private FirestationController firestationController;
//
//
//    private MockMvc mockMvc;
//
//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.openMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(firestationController).build();
//    }

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAddFirestation() throws Exception {
        // given a firestation
        Firestation firestation = new Firestation("1270 route de la plage antibes",99);

        // and given Mock service behavior
        // when(firestationService.saveFirestation(firestation)).thenReturn(firestation);

        // when Perform POST request to add firesation
        mockMvc.perform(post("/firestations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"address\": \"1270 route de la plage antibes\", \"station\": 99 }"))
                .andExpect(status().isOk())  // Verify status is OK
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))  // Verify content type is JSON
                .andExpect(jsonPath("$.address").value("1270 route de la plage antibes"))  // Verify response data
                .andExpect(jsonPath("$.station").value(99));

        // Then the service is called with the correct arguments
        //verify(firestationService).saveFirestation(firestation);
    }
}
