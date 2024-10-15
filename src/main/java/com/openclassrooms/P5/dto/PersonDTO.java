package com.openclassrooms.P5.dto;
import lombok.Data;

@Data
public class PersonDTO {
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String zip;
    private String phone;
    private String email;
    // TODO should I add a boolean isAdult ?
    // TODO should I add the fireStationNumber ?
}
