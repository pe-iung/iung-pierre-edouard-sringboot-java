package com.openclassrooms.P5.repository;
import com.openclassrooms.P5.model.MedicalRecord;
import java.util.List;


public interface MedicalRecordRepository {
    List<MedicalRecord> getMedicalRecordsById(String id);
    List<MedicalRecord> getMedicalRecords();
    void addMedicalRecord(MedicalRecord medicalRecord);
    void deleteMedicalRecordById(String id);

}
