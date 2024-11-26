package com.openclassrooms.P5.service;

import com.openclassrooms.P5.exceptions.NotFoundException;
import com.openclassrooms.P5.model.Firestation;
import com.openclassrooms.P5.model.MedicalRecord;
import com.openclassrooms.P5.repository.MedicalRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MedicalRecordServiceImpl implements MedicalRecordService{

    private final MedicalRecordRepository medicalRecordRepository;

    @Override
    public MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord) {
        medicalRecordRepository.addMedicalRecord(medicalRecord);
        return medicalRecord;
    }

    @Override
    public void deleteMedicalRecordById(String id) {
        medicalRecordRepository.deleteMedicalRecordById(id);
    }

    @Override
    public void updateMedicalRecord(MedicalRecord updatedMedicalRecord) throws NotFoundException{

        String medicalRecordId = updatedMedicalRecord.getFirstName() + "-" + updatedMedicalRecord.getLastName();
        MedicalRecord existingMedicalRecord = getMedicalRecordById(medicalRecordId)
                .orElseThrow(() -> new NotFoundException("MedicalRecord not found with id : " + medicalRecordId));

        int index = medicalRecordRepository.getMedicalRecords().indexOf(existingMedicalRecord);
        medicalRecordRepository.getMedicalRecords().set(index, updatedMedicalRecord);

    }

    @Override
    public Optional<MedicalRecord> getMedicalRecordById(String id) {
        return medicalRecordRepository.getMedicalRecordsById(id);
    }
}
