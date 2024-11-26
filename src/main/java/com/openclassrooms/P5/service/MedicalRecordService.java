package com.openclassrooms.P5.service;

import com.openclassrooms.P5.model.Firestation;
import com.openclassrooms.P5.model.MedicalRecord;

import java.util.List;
import java.util.Optional;

public interface MedicalRecordService {
    MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord);
    void deleteMedicalRecordById(String id);
    void updateMedicalRecord(MedicalRecord updatedMedicalRecord);
    Optional<MedicalRecord> getMedicalRecordById(String id);

}
