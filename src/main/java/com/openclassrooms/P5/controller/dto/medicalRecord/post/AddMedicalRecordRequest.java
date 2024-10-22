package com.openclassrooms.P5.controller.dto.medicalRecord.post;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;

/**
 * example paylaod
 *       "firstName": "John",
 *       "lastName": "Boyd",
 *       "birthdate": "03/06/1984",
 *       "medications": [
 *         "aznol:350mg",
 *         "hydrapermazol:100mg"
 *       ],
 *       "allergies": [
 *         "nillacilan"
 *       ]
 */

@Value
public class AddMedicalRecordRequest {
    @NotBlank(message = "firstName can not be null, empty or blank")
    private String firstName;

    @NotBlank(message = "lastName can not be null, empty or blank")
    private String lastName;

    @NotBlank(message = "birthdate can not be null, empty or blank and should follow this pattern MM/dd/yyyy")
    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate birthdate;

    private List<String> medications;
    private List<String> allergies;

}