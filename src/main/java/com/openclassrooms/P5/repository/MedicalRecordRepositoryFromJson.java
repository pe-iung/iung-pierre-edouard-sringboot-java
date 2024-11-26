package com.openclassrooms.P5.repository;

import com.openclassrooms.P5.exceptions.NotFoundException;
import com.openclassrooms.P5.model.MedicalRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MedicalRecordRepositoryFromJson implements MedicalRecordRepository{

    private final DataLoader dataLoader;

    @Override
    public Optional<MedicalRecord> getMedicalRecordsById(String id) throws NotFoundException {
        return dataLoader.getMedicalRecordsById(id);
    }

    @Override
    public List<MedicalRecord> getMedicalRecords() {
        return this.dataLoader.getMedicalRecords();
    }

    @Override
    public void addMedicalRecord(MedicalRecord medicalRecord) {
        dataLoader.addMedicalRecord(medicalRecord);
    }

    /**
     *
     * @param id is the concatenation of "firstname" + "-" + "lastname"
     * @return void
     */
    @Override
    public void deleteMedicalRecordById(String id) {
        dataLoader.deleteMedicalRecordById(id);
    }
}