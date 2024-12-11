package com.openclassrooms.P5.dto.person;

import com.openclassrooms.P5.model.Person;

public record PersonWithPhoneAndAddress (
        String firstname,
        String lastname,
        String phone,
        String address
){

    public PersonWithPhoneAndAddress(Person person){
        this(   person.getFirstName(),
                person.getLastName(),
                person.getPhone(),
                person.getAddress());
    }

}
