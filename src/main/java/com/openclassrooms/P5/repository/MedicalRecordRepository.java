package com.openclassrooms.P5.repository;
import com.openclassrooms.P5.model.MedicalRecord;
import java.util.List;


public interface MedicalRecordRepository {
    List<MedicalRecord> getMedicalRecordByFirstnameAndLastname(String firstname, String lastname);

    List<MedicalRecord> getMedicalRecords();

    MedicalRecord addMedicalRecord(MedicalRecord medicalRecord);
    boolean deleteMedicalRecordById(String id);

}
