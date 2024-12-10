package com.openclassrooms.P5.repository;

import com.openclassrooms.P5.exceptions.NotFoundException;
import com.openclassrooms.P5.model.MedicalRecord;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MedicalRecordRepositoryFromJson implements MedicalRecordRepository{

    private final DataLoader dataLoader;

    @Override
    public Optional<MedicalRecord> findMedicalRecordById(String id) throws NotFoundException {
        List<MedicalRecord> medicalRecords = dataLoader.getMedicalRecords();
        Optional<MedicalRecord> medicalRecordFound = medicalRecords
                .stream()
                .filter(f -> (f.getId().equals(id)))
                .findFirst();
        return medicalRecordFound;
    }

    @Override
    public List<MedicalRecord> getMedicalRecords() {
        return this.dataLoader.getMedicalRecords();
    }

    @Override
    public void addMedicalRecord(MedicalRecord medicalRecord) {
        dataLoader.getMedicalRecords().add(medicalRecord);
        log.info("New Medicalrecord added: {}", medicalRecord);
    }

    /**
     *
     * @param id is the concatenation of "firstname" + "-" + "lastname"
     * @return void
     */
    @Override
    public void deleteMedicalRecordById(String id) {
        //dataLoader.deleteMedicalRecordById(id);
        List<MedicalRecord> medicalRecords = dataLoader.getMedicalRecords();
        boolean removed = medicalRecords.removeIf(
                medicalRecord -> medicalRecord.getId().equals(id));
        if (removed) {
            log.info("Medical record removed for id = first-lastname: {}", id);
        } else {
            log.warn("No medical record found for id = first-lastname: {}", id);
        }
    }
}