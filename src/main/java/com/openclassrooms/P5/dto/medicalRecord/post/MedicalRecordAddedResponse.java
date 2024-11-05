package com.openclassrooms.P5.dto.medicalRecord.post;


import com.openclassrooms.P5.model.Firestation;
import com.openclassrooms.P5.model.MedicalRecord;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;

@Value
public class MedicalRecordAddedResponse {

    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private List<String> medications;
    private List<String> allergies;

    public MedicalRecordAddedResponse(MedicalRecord medicalRecord){
        this.firstName = medicalRecord.getFirstName();
        this.lastName = medicalRecord.getLastName();
        this.birthdate = medicalRecord.getBirthdate();
        this.medications = medicalRecord.getMedications();
        this.allergies = medicalRecord.getAllergies();

    }
}
