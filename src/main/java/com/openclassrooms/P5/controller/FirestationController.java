package com.openclassrooms.P5.controller;

import com.openclassrooms.P5.dto.FirestationDTO;
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

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/firestations")
    public List<FirestationDTO> getAllFirestations(){
        return firestationServiceImpl.getAllFirestationDTO();
    }

    @PostMapping("/firestations")
    public ResponseEntity<?> addFirestation(@Validated @RequestBody AddFirestationRequest firestationRequest) {
    // TODO : ask if it's better to return ResponseEntity<FirestationAddedResponse> to use DTO object instead of Firestation one

        Firestation firestation = new Firestation(firestationRequest.getAddress(), firestationRequest.getStation());

        Firestation savedFirestation =  firestationServiceImpl.saveFirestation(firestation);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFirestation);
    }


    @DeleteMapping("/firestations/{address}")
    public ResponseEntity<?> deleteFirestation(@PathVariable @Validated @NotBlank String address) {
        firestationServiceImpl.deleteFirestationByAddress(address);

        //TODO : check if response entity is correct for deletion
        return ResponseEntity.status(HttpStatus.OK).body(address);
    }

    // response ok
    @PutMapping("/firestations")
    public ResponseEntity<?> updateFirestation(@Validated @RequestBody UpdateFirestationRequest firestationRequest){
        // TODO 1/ ask if moving logic to service was done "properly"
        // TODO 2/ should I send different httpStatus if the firestation was not found ? how?
        // TODO 3/ is it better to use ResponseEntity<UpdateFirestationRequest> or ResponseEntity<Firestation> or ResponseEntity<FirestationupdateResponse>

        firestationServiceImpl.updateFirestationRequestIfAdressExist(firestationRequest);
        return ResponseEntity.status(HttpStatus.OK).body(firestationRequest);
    }
}
