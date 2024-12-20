package com.openclassrooms.P5.service;

import com.openclassrooms.P5.exceptions.ConflictException;
import com.openclassrooms.P5.exceptions.NotFoundException;
import com.openclassrooms.P5.model.MedicalRecord;
import com.openclassrooms.P5.repository.MedicalRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;

    /**
     * save a medicalMecord and prevent saving duplicated medicalRecordId = firstname-lastname
     * @param medicalRecord
     * @return medicalRecord
     */
    @Override
    public MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord) {
        if (getMedicalRecordById(medicalRecord.getId()).isPresent()) {
            throw new ConflictException("Medical record already exist for firstname-lastname ="+medicalRecord.getId());
        }
        medicalRecordRepository.addMedicalRecord(medicalRecord);
        return medicalRecord;
    }

    /**
     * delete medical record by id = firstname-lastname
     * @param id
     */
    @Override
    public void deleteMedicalRecordById(String id) {
        medicalRecordRepository.deleteMedicalRecordById(id);
    }

    /**
     * update medicalRecord for given id=firstname-lastname
     * @param updatedMedicalRecord
     * @throws NotFoundException
     */
    @Override
    public void updateMedicalRecord(MedicalRecord updatedMedicalRecord) throws NotFoundException {

        String medicalRecordId = updatedMedicalRecord.getFirstName() + "-" + updatedMedicalRecord.getLastName();
        MedicalRecord existingMedicalRecord = getMedicalRecordById(medicalRecordId)
                .orElseThrow(() -> new NotFoundException("MedicalRecord not found with id : " + medicalRecordId));

        int index = medicalRecordRepository.getMedicalRecords().indexOf(existingMedicalRecord);
        medicalRecordRepository.getMedicalRecords().set(index, updatedMedicalRecord);

    }

    /**
     * get medical record by id=firstname-lastname
     * @param id
     * @return an optional medical record
     */
    @Override
    public Optional<MedicalRecord> getMedicalRecordById(String id) {
        return medicalRecordRepository.findMedicalRecordById(id);
    }
}
