package com.openclassrooms.P5.model;

public record PersonWithMedicalRecord(
        Person person,
        MedicalRecord medicalRecord
){

    public PersonWithMedicalRecord {
        if(!person.getId().equals(medicalRecord.getId())){
            throw new RuntimeException("Not compatible person with medicalrecord");

        }
    }

    public String getId(){
        return person.getId();
    }

}
