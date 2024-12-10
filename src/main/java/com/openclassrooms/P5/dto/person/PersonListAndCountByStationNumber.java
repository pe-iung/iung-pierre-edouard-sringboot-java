package com.openclassrooms.P5.dto.person;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.openclassrooms.P5.model.PersonWithMedicalRecord;

import java.util.List;

public record PersonListAndCountByStationNumber(
        List<PersonWithPhoneAndAddress> personWithPhoneAndAddressLists,
        long counterMinor) {

    public PersonListAndCountByStationNumber(List<PersonWithMedicalRecord> persons){
        this( persons.stream().map(p -> new PersonWithPhoneAndAddress(p.person())).toList(),
              persons.stream().filter(p -> p.medicalRecord().isMinor()).count()
        );

    }

    @JsonProperty
    public long counterMajor(){
        return personWithPhoneAndAddressLists.size() - counterMinor;
    }

}
