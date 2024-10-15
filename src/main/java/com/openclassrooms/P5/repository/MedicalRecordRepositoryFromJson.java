package com.openclassrooms.P5.repository;

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
    public List<MedicalRecord> getMedicalRecordByFirstnameAndLastname(String firstname, String lastname) {
        return this.getMedicalRecords()
                .stream()
                .filter(f -> (f.getFirstName().equals(firstname)))
                .filter(f -> (f.getLastName().equals(lastname)))
                .toList();
    }

    @Override
    public List<MedicalRecord> getMedicalRecords() {
        return this.dataLoader.getMedicalrecords();
    }
}