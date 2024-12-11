package com.openclassrooms.P5.repository;

import com.openclassrooms.P5.model.MedicalRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class MedicalRecordRepositoryTest {

    @Mock
    DataLoader dataLoader;

    @InjectMocks
    MedicalRecordRepositoryFromJson medicalRecordRepositoryFromJson;

    @Test
    public void testgetMedicalRecordById() {
        //Given an Id
        String id = "John-Doe";

        //When we get the medical record by ID
        medicalRecordRepositoryFromJson.findMedicalRecordById(id);

        //then the medical record is loaded from the dataloader
        verify(dataLoader, times(1)).getMedicalRecords();

    }

    @Test
    public void testgetMedicalRecords(){

        //given a dataloader with medicalRecords

        //when getMedicalRecord method is called
        medicalRecordRepositoryFromJson.getMedicalRecords();

        //then dataloader is called to get medicalRecords
        verify(dataLoader, times(1)).getMedicalRecords();


    }

    @Test
    public void testaddMedicalRecord(){

        //given a medicalRecord
        MedicalRecord medicalRecord = new MedicalRecord(
                "john",
                "Doe",
                LocalDate.of(1950, 12, 12),
                List.of("Antihistamine"),
                List.of("peanut")
        );

        //when getMedicalRecord method is called
        medicalRecordRepositoryFromJson.addMedicalRecord(medicalRecord);

        //then dataloader is called to get medicalRecords
        verify(dataLoader, times(1)).getMedicalRecords();

    }

    @Test
    public void testdeleteMedicalRecordById(){

        //Given an Id
        String id = "John-Doe";

        //When we get the medical record by ID
        medicalRecordRepositoryFromJson.deleteMedicalRecordById(id);

        //then the medical record is loaded from the dataloader
        verify(dataLoader, times(1)).getMedicalRecords();


    }
}
