package com.openclassrooms.P5;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.P5.dto.firestation.post.AddFirestationRequest;
import com.openclassrooms.P5.dto.medicalRecord.post.AddMedicalRecordRequest;
import com.openclassrooms.P5.model.Firestation;
import com.openclassrooms.P5.model.MedicalRecord;
import com.openclassrooms.P5.repository.MedicalRecordRepository;
import net.bytebuddy.asm.Advice;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.params.provider.Arguments.of;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class MedicalRecordControllerTest {

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAddMedicalRecord() throws Exception {
        // Given a Medical Record
        final String firstName = "Bob";
        final String lastName = "Marley";
        final LocalDate birthdate = LocalDate.of(1940, 11, 11);
        final List<String> medications = List.of();
        final List<String> allergies = List.of("peanut");

        final AddMedicalRecordRequest addMedicalRecordRequest = new AddMedicalRecordRequest(firstName, lastName, birthdate, medications, allergies);
        final MedicalRecord expectedResult = new MedicalRecord(firstName, lastName, birthdate, medications, allergies);

        // When performing a POST request on medidicalRecords
        final ResultActions response = mockMvc.perform(post("/medicalRecords")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(addMedicalRecordRequest)));

        // Then
        response.andExpect(status().isOk())  // Verify status is OK
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))  // Verify content type is JSON
                .andExpect(jsonPath("$.firstName").value(firstName))  // Verify response data
                .andExpect(jsonPath("$.lastName").value(lastName))
                //TODO how to check a date ?
                // .andExpect(jsonPath("$.birthdate").value(birthdate).toString())
                .andExpect(jsonPath("$.birthdate").value(birthdate.toString()))
                .andExpect(jsonPath("$.medications").isEmpty())
                .andExpect(jsonPath("$.allergies[0]").value(allergies.get(0)));

        final List<MedicalRecord> savedResponse = medicalRecordRepository.getMedicalRecordByFirstnameAndLastname(firstName,lastName);
        Assertions.assertThat(savedResponse)
                .hasSize(1)
                .contains(expectedResult);

    }

}
