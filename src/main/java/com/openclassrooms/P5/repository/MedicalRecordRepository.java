package com.openclassrooms.P5.repository;
import com.openclassrooms.P5.model.MedicalRecord;
import java.util.List;
import java.util.Optional;


public interface MedicalRecordRepository {
    Optional<MedicalRecord> findMedicalRecordById(String id);
    List<MedicalRecord> getMedicalRecords();
    void addMedicalRecord(MedicalRecord medicalRecord);
    void deleteMedicalRecordById(String id);

}
