package com.openclassrooms.P5.controller;

import com.openclassrooms.P5.controller.dto.*;
import com.openclassrooms.P5.model.Firestation;
import com.openclassrooms.P5.service.FirestationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    private final FirestationService firestationService;

    @GetMapping("/firestations")
    public List<FirestationDTO> getAllFirestations(){
        return firestationService.getAllFirestationDTO();
    }

    @PostMapping("/firestations")
    public FirestationAddedResponse addFirestation(@Validated @RequestBody AddFirestationRequest firestationRequest) {

        Firestation firestation = new Firestation(firestationRequest.getAddress(), firestationRequest.getStation());

        Firestation savedFirestation =  firestationService.saveFirestation(firestation);

        return new FirestationAddedResponse(savedFirestation);
    }


    @DeleteMapping("/firestations/{address}")
    public void deleteFirestation(@PathVariable String address) {
        firestationService.deleteFirestationByAddress(address);
    }
}
