package com.openclassrooms.P5.service;

import com.openclassrooms.P5.controller.dto.FirestationDTO;
import com.openclassrooms.P5.model.Firestation;
import com.openclassrooms.P5.repository.FirestationRepositoryFromJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FirestationService {

    @Autowired
    private FirestationRepositoryFromJson firestationRepositoryFromJson;

    public List<FirestationDTO> getAllFirestationDTO(){
        return firestationRepositoryFromJson.getFirestations()
                .stream()
                .map(this::convertRepositoryToDTO)
                .collect(Collectors.toList());
    }

    private FirestationDTO convertRepositoryToDTO (Firestation firestation){
        FirestationDTO firestationDTO = new FirestationDTO();
        firestationDTO.setAddress(firestation.getAddress());
        firestationDTO.setStation(firestation.getStation());

        return firestationDTO;
    }

    public Firestation saveFirestation(Firestation firestation) {
        firestationRepositoryFromJson.addFirestation(firestation);
        return firestation;
    }

    public void deleteFirestationByAddress(String address) {
        firestationRepositoryFromJson.deleteFirestationByAddress(address);  // Assuming the repository has a delete method
    }
}
