package com.openclassrooms.P5.dto.person;

import com.openclassrooms.P5.model.PersonWithMedicalRecord;

import java.util.List;

public record PersonWithPhoneAgeMedicationsAllergies(
        String firstName,
        String lastName,
        String telephopne,
        int age,
        List<String> medications,
        List<String> allergies
) {
    public PersonWithPhoneAgeMedicationsAllergies(PersonWithMedicalRecord person) {
        this(
                person.person().getFirstName(),
                person.person().getLastName(),
                person.person().getPhone(),
                person.medicalRecord().getAge(),
                person.medicalRecord().getMedications(),
                person.medicalRecord().getAllergies()
        );

    }
}
