package com.openclassrooms.P5.controller;

import com.openclassrooms.P5.dto.medicalRecord.post.AddMedicalRecordRequest;
import com.openclassrooms.P5.dto.medicalRecord.post.MedicalRecordAddedResponse;
import com.openclassrooms.P5.model.MedicalRecord;
import com.openclassrooms.P5.service.MedicalRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;

    @PostMapping("/medicalRecords")
    public MedicalRecordAddedResponse addMedicalRecord(@Validated @RequestBody AddMedicalRecordRequest addMedicalRecordRequest) {

        MedicalRecord medicalRecord = new MedicalRecord(
                addMedicalRecordRequest.getFirstName(),
                addMedicalRecordRequest.getLastName(),
                addMedicalRecordRequest.getBirthdate(),
                addMedicalRecordRequest.getMedications(),
                addMedicalRecordRequest.getAllergies()
        );

        MedicalRecord savedMedicalRecord =  medicalRecordService.saveMedicalRecord(medicalRecord);

        return new MedicalRecordAddedResponse(savedMedicalRecord);
    }

    /**
     *
     * @param id = string concatenation of "firstname" + "-" + "lastname", eg "john-doe"
     */
    @DeleteMapping("/medicalRecord/{id}")
    public void deleteMedicalRecordById(@PathVariable String id) {
        medicalRecordService.deleteMedicalRecordById(id);
    }
}
