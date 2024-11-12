package com.openclassrooms.P5.service;

import com.openclassrooms.P5.model.MedicalRecord;

public interface MedicalRecordService {
    public MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord);
    public void deleteMedicalRecordById(String id);

}
