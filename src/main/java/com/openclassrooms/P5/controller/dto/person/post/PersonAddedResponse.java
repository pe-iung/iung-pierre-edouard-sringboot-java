package com.openclassrooms.P5.controller.dto.person.post;

import com.openclassrooms.P5.model.Person;
import lombok.Value;

@Value
public class PersonAddedResponse {
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String zip;
    private String phone;
    private String email;

    public PersonAddedResponse(Person person) {
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.address = person.getAddress();
        this.city = person.getCity();
        this.zip = person.getZip();
        this.phone = person.getPhone();
        this.email = person.getEmail();
    }
}
