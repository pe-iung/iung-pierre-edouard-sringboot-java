package com.openclassrooms.P5.dto.person.put;

import jakarta.validation.constraints.NotBlank;
import lombok.Value;

@Value
public class UpdatePersonRequest {

    @NotBlank(message = "FirstName can not be null, empty or blank")
    private String firstName;

    @NotBlank(message = "LastName can not be null, empty or blank")
    private String lastName;

    @NotBlank(message = "Address can not be null, empty or blank")
    private String address;

    @NotBlank(message = "City can not be null, empty or blank")
    private String city;

    @NotBlank(message = "Zip can not be null, empty or blank")
    private String zip;

    @NotBlank(message = "Phone can not be null, empty or blank")
    private String phone;

    @NotBlank(message = "Email can not be null, empty or blank")
    private String email;
}
