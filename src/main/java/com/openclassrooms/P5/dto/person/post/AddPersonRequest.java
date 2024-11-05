package com.openclassrooms.P5.dto.person.post;

import jakarta.validation.constraints.NotBlank;
import lombok.Value;

@Value
public class AddPersonRequest {

    @NotBlank(message = "firstName can not be null, empty or blank")
    private String firstName;

    @NotBlank(message = "lastName can not be null, empty or blank")
    private String lastName;

    @NotBlank(message = "address can not be null, empty or blank")
    private String address;

    @NotBlank(message = "city can not be null, empty or blank")
    private String city;

    @NotBlank(message = "zip can not be null, empty or blank")
    private String zip;

    @NotBlank(message = "phone can not be null, empty or blank")
    private String phone;

    @NotBlank(message = "email can not be null, empty or blank")
    private String email;
}
