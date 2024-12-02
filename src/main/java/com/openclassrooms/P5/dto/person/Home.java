package com.openclassrooms.P5.dto.person;

import java.util.List;


public record Home(
        String address,
        List<PersonWithPhoneAgeMedicationsAllergies> personWithPhoneAgeMedicationsAllergies
) {
    public Home(List<PersonWithPhoneAgeMedicationsAllergies> persons, String address) {
        this(
                address,
                persons);

    }
}