package com.openclassrooms.P5.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.P5.dto.medicalRecord.post.AddMedicalRecordRequest;
import com.openclassrooms.P5.dto.medicalRecord.put.UpdateMedicalRecordRequest;
import com.openclassrooms.P5.model.MedicalRecord;
import com.openclassrooms.P5.repository.MedicalRecordRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class MedicalRecordIT {

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

        // Then verify the response data is as expected
        response.andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value(firstName))
                .andExpect(jsonPath("$.lastName").value(lastName))
                .andExpect(jsonPath("$.birthdate").value("11/11/1940"))
                .andExpect(jsonPath("$.medications").isEmpty())
                .andExpect(jsonPath("$.allergies[0]").value(allergies.get(0)));

        // Then verify the data was successfully added tyo the repository
        // an id is defined by the string_id = "first-Lastname"
        final Optional<MedicalRecord> savedResponse = medicalRecordRepository.findMedicalRecordById(firstName + "-" + lastName);
        Assertions.assertThat(savedResponse)
                .contains(expectedResult);

    }

    @Test
    public void testUpdateMedicalRecord() throws Exception {

        // Given a Medical Record
        DateTimeFormatter testDateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        final String firstName = "Boby";
        final String lastName = "Marlot";
        final LocalDate birthdate = LocalDate.of(1940, 11, 11);
        final String birthdayFormated = "11/11/1940";
        final List<String> medications = List.of();
        final List<String> allergies = List.of("peanut");
        MedicalRecord medicalRecord = new MedicalRecord(
                firstName,
                lastName,
                birthdate,
                medications,
                allergies
        );
        List<String> updatedMedications = List.of("doliprane");
        List<String> updatedAllergies = List.of("peanut", "fish");
        medicalRecordRepository.addMedicalRecord(medicalRecord);
        final UpdateMedicalRecordRequest updateMedicalRecordRequest =
                new UpdateMedicalRecordRequest(
                        firstName, lastName, birthdate, updatedMedications, updatedAllergies);

        final MedicalRecord expectedResult = new MedicalRecord(firstName, lastName, birthdate, updatedMedications, updatedAllergies);

        // When performing a POST request on medidicalRecords
        final ResultActions response = mockMvc.perform(put("/medicalRecords")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateMedicalRecordRequest)));

        // Then verify the response data is as expected
        response.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value(firstName))
                .andExpect(jsonPath("$.lastName").value(lastName))
                .andExpect(jsonPath("$.birthdate").value(birthdayFormated))
                .andExpect(jsonPath("$.medications[0]").value(updatedMedications.get(0)))
                .andExpect(jsonPath("$.allergies[0]").value(updatedAllergies.get(0)))
                .andExpect(jsonPath("$.allergies[1]").value(updatedAllergies.get(1)));

        // Then verify the data was successfully added tyo the repository
        // an id is defined by the string_id = "first-Lastname"
        final Optional<MedicalRecord> savedResponse = medicalRecordRepository.findMedicalRecordById(firstName + "-" + lastName);
        Assertions.assertThat(savedResponse)
                .contains(expectedResult);

    }

    @Test
    public void testDeleteMedicalRecords() throws Exception {
        // Given a Medical Record
        final String firstName = "Bobo";
        final String lastName = "Marlou";
        final LocalDate birthdate = LocalDate.of(1940, 11, 11);
        final String birthdayFormated = "11/11/1940";
        final List<String> medications = List.of();
        final List<String> allergies = List.of("peanut");

        MedicalRecord medicalRecord = new MedicalRecord(
                firstName,
                lastName,
                birthdate,
                medications,
                allergies
        );
        String id = firstName+"-"+lastName;
        medicalRecordRepository.addMedicalRecord(medicalRecord);
        //we check the record exist before delete
        Assertions.assertThat(medicalRecordRepository.findMedicalRecordById(id))
                .contains(medicalRecord);

        // When performing a DELETE request on medidicalRecords
        final ResultActions response = mockMvc.perform(delete("/medicalRecord/"+id)
                .contentType(MediaType.APPLICATION_JSON));

        // Then verify the data was successfully deleted to the repository
        // an id is defined by the string_id = "first-Lastname"
        final Optional<MedicalRecord> savedResponse = medicalRecordRepository.findMedicalRecordById(id);
        Assertions.assertThat(savedResponse)
                .isEmpty();

    }

}
