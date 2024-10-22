package com.openclassrooms.P5.service;

import com.openclassrooms.P5.model.MedicalRecord;
import com.openclassrooms.P5.model.Person;
import com.openclassrooms.P5.repository.MedicalRecordRepositoryFromJson;
import com.openclassrooms.P5.repository.PersonRepositoryFromJson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MedicalRecordService {

    private final MedicalRecordRepositoryFromJson medicalRecordRepositoryFromJson;

    public MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord) {
        medicalRecordRepositoryFromJson.addMedicalRecord(medicalRecord);
        return medicalRecord;
    }

    public void deleteMedicalRecordById(String id) {
        medicalRecordRepositoryFromJson.deleteMedicalRecordById(id);
    }
}
