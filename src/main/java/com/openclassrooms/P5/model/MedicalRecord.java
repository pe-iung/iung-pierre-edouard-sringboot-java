package com.openclassrooms.P5.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.Data;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Data
public class MedicalRecord {
    private String firstName;
    private String lastName;

    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate birthdate;

    private List<String> medications;
    private List<String> allergies;


    public String getId(){
        return firstName + "-" + lastName;
    }


    public Integer getAge(){
        return Period.between(birthdate, LocalDate.now()).getYears();
    }


    public boolean isMinor(){
        return getAge() <= 18;
    }

    public boolean isMajor(){
        return !isMinor();
    }

}
