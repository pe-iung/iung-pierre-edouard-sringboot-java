package com.openclassrooms.P5.service;

import com.openclassrooms.P5.model.MedicalRecord;
import com.openclassrooms.P5.repository.MedicalRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;

    public MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord) {
        medicalRecordRepository.addMedicalRecord(medicalRecord);
        return medicalRecord;
    }

    public void deleteMedicalRecordById(String id) {
        medicalRecordRepository.deleteMedicalRecordById(id);
    }
}
