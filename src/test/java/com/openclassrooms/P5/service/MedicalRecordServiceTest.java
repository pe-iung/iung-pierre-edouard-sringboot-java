package com.openclassrooms.P5.service;
import com.openclassrooms.P5.model.MedicalRecord;
import com.openclassrooms.P5.repository.MedicalRecordRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MedicalRecordServiceTest {

    @Mock
    private static MedicalRecordRepository medicalRecordRepository;

    private MedicalRecordServiceImpl medicalRecordService;

    private final MedicalRecord medicalRecord = new MedicalRecord(
            "bob",
            "eponge",
            LocalDate.of(2000, 12, 12),
            List.of("medications"),
            List.of("allergies"));

    @Test
    public void testSaveMedicalRecord() {
        //given a medicalRecord

        //when saving a medicalRecord in service
        MedicalRecordServiceImpl medicalRecordService = new MedicalRecordServiceImpl(medicalRecordRepository);
        medicalRecordService.saveMedicalRecord(medicalRecord);

        // then the station is added to the repository
        verify(medicalRecordRepository, times(1)).addMedicalRecord(any(MedicalRecord.class));
        assertNotNull(medicalRecordRepository.findMedicalRecordById("bob-eponge"));
    }
}
