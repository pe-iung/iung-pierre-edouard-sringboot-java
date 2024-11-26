package com.openclassrooms.P5.controller;

import com.openclassrooms.P5.dto.firestation.put.UpdateFirestationRequest;
import com.openclassrooms.P5.dto.medicalRecord.post.AddMedicalRecordRequest;
import com.openclassrooms.P5.dto.medicalRecord.post.MedicalRecordAddedResponse;
import com.openclassrooms.P5.dto.medicalRecord.put.UpdateMedicalRecordRequest;
import com.openclassrooms.P5.model.Firestation;
import com.openclassrooms.P5.model.MedicalRecord;
import com.openclassrooms.P5.service.MedicalRecordServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class MedicalRecordController {

    private final MedicalRecordServiceImpl medicalRecordServiceImpl;

    @PostMapping("/medicalRecords")
    public MedicalRecordAddedResponse addMedicalRecord(@Validated @RequestBody AddMedicalRecordRequest addMedicalRecordRequest) {

        MedicalRecord medicalRecord = new MedicalRecord(
                addMedicalRecordRequest.getFirstName(),
                addMedicalRecordRequest.getLastName(),
                addMedicalRecordRequest.getBirthdate(),
                addMedicalRecordRequest.getMedications(),
                addMedicalRecordRequest.getAllergies()
        );

        MedicalRecord savedMedicalRecord =  medicalRecordServiceImpl.saveMedicalRecord(medicalRecord);

        return new MedicalRecordAddedResponse(savedMedicalRecord);
    }

    /**
     *
     * @param id = string concatenation of "firstname" + "-" + "lastname", eg "john-doe"
     */
    @DeleteMapping("/medicalRecord/{id}")
    public void deleteMedicalRecordById(@PathVariable String id) {
        medicalRecordServiceImpl.deleteMedicalRecordById(id);
    }

    @PutMapping("/medicalRecords")
    public ResponseEntity<?> updateLedicalRecord(@Validated @RequestBody UpdateMedicalRecordRequest medicalRecordRequest){

        MedicalRecord updatedMedicalRecord = new MedicalRecord(
                medicalRecordRequest.getFirstName(),
                medicalRecordRequest.getLastName(),
                medicalRecordRequest.getBirthdate(),
                medicalRecordRequest.getMedications(),
                medicalRecordRequest.getAllergies());
        medicalRecordServiceImpl.updateMedicalRecord(updatedMedicalRecord);

        return ResponseEntity.status(HttpStatus.OK).body(medicalRecordRequest);
    }
}
