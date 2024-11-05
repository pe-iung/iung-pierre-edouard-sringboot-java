package com.openclassrooms.P5.dto.firestation.put;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Value;

@Value
public class UpdateFirestationRequest {
    @NotBlank(message = "Address can not be null, empty or blank")
    private String address;

    @Positive(message = "Station can not be negative or equal to 0")
    private int station;

}