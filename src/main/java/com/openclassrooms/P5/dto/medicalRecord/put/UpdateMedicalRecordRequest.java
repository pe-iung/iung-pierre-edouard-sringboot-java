package com.openclassrooms.P5.dto.medicalRecord.put;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;

@Value
public class UpdateMedicalRecordRequest {
    
    @NotBlank(message = "FirstName can not be null, empty or blank")
    private String firstName;

    @NotBlank(message = "LastName can not be null, empty or blank")
    private String lastName;

    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate birthdate;


    private List<String> medications;
    private List<String> allergies;

}