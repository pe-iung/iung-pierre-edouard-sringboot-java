package com.openclassrooms.P5.controller;

import com.openclassrooms.P5.dto.medicalRecord.post.AddMedicalRecordRequest;
import com.openclassrooms.P5.dto.medicalRecord.post.MedicalRecordAddedResponse;
import com.openclassrooms.P5.dto.medicalRecord.put.UpdateMedicalRecordRequest;
import com.openclassrooms.P5.model.MedicalRecord;
import com.openclassrooms.P5.service.MedicalRecordServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
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

        log.info("a medical record was added for firstname ={} and lastname = {}",
                addMedicalRecordRequest.getFirstName(),
                addMedicalRecordRequest.getLastName()
                );

        log.debug("this addMedicalRequest was saved = {}", savedMedicalRecord);

        return new MedicalRecordAddedResponse(savedMedicalRecord);
    }

    /**
     *
     * @param id = string concatenation of "firstname" + "-" + "lastname", eg "john-doe"
     */
    @DeleteMapping("/medicalRecord/{id}")
    public void deleteMedicalRecordById(@PathVariable String id) {
        medicalRecordServiceImpl.deleteMedicalRecordById(id);
        log.info("this medical id was deleted = {}",id);
    }

    @PutMapping("/medicalRecords")
    public ResponseEntity<?> updateMedicalRecord(@Validated @RequestBody UpdateMedicalRecordRequest medicalRecordRequest){

        MedicalRecord updatedMedicalRecord = new MedicalRecord(
                medicalRecordRequest.getFirstName(),
                medicalRecordRequest.getLastName(),
                medicalRecordRequest.getBirthdate(),
                medicalRecordRequest.getMedications(),
                medicalRecordRequest.getAllergies());
        medicalRecordServiceImpl.updateMedicalRecord(updatedMedicalRecord);

        log.info("a medical record was updated for firstname ={} and lastname ={}",
                medicalRecordRequest.getFirstName(),
                medicalRecordRequest.getLastName()

                );
        log.debug("this update medical record request was received = {}", updatedMedicalRecord);

        return ResponseEntity.status(HttpStatus.OK).body(medicalRecordRequest);
    }
}
