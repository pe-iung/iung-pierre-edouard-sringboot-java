package com.openclassrooms.P5.dto.person;

import java.util.List;

public record PersonInfoLastName(
        String firstname,
        String lastname,
        String address,
        int age,
        String email,
        List<String> medications,
        List<String> allergies

) {
}
