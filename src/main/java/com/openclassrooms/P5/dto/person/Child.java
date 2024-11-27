package com.openclassrooms.P5.dto.person;

import com.openclassrooms.P5.model.PersonWithMedicalRecord;

import java.util.List;

public record Child(
        String firstName,
        String lastName,
        int age,
        List<Family> family
) {

    public Child(PersonWithMedicalRecord minor, List<PersonWithMedicalRecord> family){
        this(
                minor.person().getFirstName(),
                minor.person().getLastName(),
                minor.medicalRecord().getAge(),
                family.stream()
                        .filter(p -> !p.getId().equals(minor.getId()))
                        .map(Family::new)
                        .toList()
        );
    }

    record Family (
            String firstName,
            String lastName,
            int age)
    {
        public Family( PersonWithMedicalRecord member){
            this(
                    member.person().getFirstName(),
                    member.person().getLastName(),
                    member.medicalRecord().getAge()
            );
        }

    }
}