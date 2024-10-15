package com.openclassrooms.P5.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.List;

public class MedicalRecordDTO {
    private String firstName;
    private String lastName;

    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate birthdate;

    private List<String> medications;
    private List<String> allergies;
}
