package com.openclassrooms.P5.controller;

import com.openclassrooms.P5.dto.firestation.post.AddFirestationRequest;
import com.openclassrooms.P5.dto.firestation.put.UpdateFirestationRequest;
import com.openclassrooms.P5.model.Firestation;
import com.openclassrooms.P5.service.FirestationServiceImpl;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class FirestationController {

    private final FirestationServiceImpl firestationServiceImpl;

    @PostMapping("/firestations")
    public ResponseEntity<?> addFirestation(@Validated @RequestBody AddFirestationRequest firestationRequest) {


        Firestation firestation = new Firestation(firestationRequest.getAddress(), firestationRequest.getStation());

        Firestation savedFirestation =  firestationServiceImpl.saveFirestation(firestation);
        log.info("a firestation was added");
        log.debug("this firestation request was received {}", firestationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFirestation);
    }


    @DeleteMapping("/firestations/{address}")
    public ResponseEntity<?> deleteFirestation(@PathVariable @Validated @NotBlank String address) {
        firestationServiceImpl.deleteFirestationByAddress(address);
        return ResponseEntity.status(HttpStatus.OK).body(address);
    }


    @PutMapping("/firestations")
    public ResponseEntity<?> updateFirestation(@Validated @RequestBody UpdateFirestationRequest firestationRequest){

        Firestation updatedFirestation = new Firestation(firestationRequest.getAddress(),firestationRequest.getStation());
        firestationServiceImpl.updateFirestation(updatedFirestation);
        log.info("a firestation was updated at this address = {}", firestationRequest.getAddress());
        log.debug("this firestation request update was received = {}",firestationRequest);

        return ResponseEntity.status(HttpStatus.OK).body(firestationRequest);
    }
}
