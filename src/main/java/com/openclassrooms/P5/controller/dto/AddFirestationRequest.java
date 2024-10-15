package com.openclassrooms.P5.controller.dto;

import com.openclassrooms.P5.model.Firestation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.Value;

@Value
public class AddFirestationRequest {
    @NotBlank(message = "Address can not be null, empty or blank")
    private String address;

    @Positive(message = "Station can not be negative or equal to 0")
    private int station;

}
