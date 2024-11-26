package com.openclassrooms.P5.controller;

import com.openclassrooms.P5.dto.firestation.post.AddFirestationRequest;
import com.openclassrooms.P5.dto.firestation.put.UpdateFirestationRequest;
import com.openclassrooms.P5.model.Firestation;
import com.openclassrooms.P5.service.FirestationServiceImpl;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/*
http://localhost:8080/firestation?stationNumber=<station_number>
Cette url doit retourner une liste des personnes couvertes par la caserne de pompiers
correspondante. Donc, si le numéro de station = 1, elle doit renvoyer les habitants
couverts par la station numéro 1. La liste doit inclure les informations spécifiques
suivantes : prénom, nom, adresse, numéro de téléphone. De plus, elle doit fournir un
décompte du nombre d'adultes et du nombre d'enfants (tout individu âgé de 18 ans ou
moins) dans la zone desservie.
 */
@RequiredArgsConstructor
@RestController
public class FirestationController {

    private final FirestationServiceImpl firestationServiceImpl;

    @PostMapping("/firestations")
    public ResponseEntity<?> addFirestation(@Validated @RequestBody AddFirestationRequest firestationRequest) {


        Firestation firestation = new Firestation(firestationRequest.getAddress(), firestationRequest.getStation());

        Firestation savedFirestation =  firestationServiceImpl.saveFirestation(firestation);
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

        return ResponseEntity.status(HttpStatus.OK).body(firestationRequest);
    }
}
