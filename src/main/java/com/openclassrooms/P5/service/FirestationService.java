package com.openclassrooms.P5.service;

import com.openclassrooms.P5.dto.FirestationDTO;
import com.openclassrooms.P5.dto.firestation.put.FirestationUpdatedResponse;
import com.openclassrooms.P5.model.Firestation;
import com.openclassrooms.P5.repository.FirestationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FirestationService implements FirestationServiceInterface {

    private final FirestationRepository firestationRepository;

    public List<FirestationDTO> getAllFirestationDTO(){
        return firestationRepository.getFirestations()
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
        if(getFirestationByAdress(firestation.getAddress()).isPresent()){
            throw new RuntimeException("Address already used");
        }

        firestationRepository.addFirestation(firestation);
        return firestation;
    }

    public void deleteFirestationByAddress(String address) {
        firestationRepository.deleteFirestationByAddress(address);  // Assuming the repository has a delete method
    }

    public Optional<Firestation> getFirestationByAdress(String address) {
        Optional<Firestation> searchedStation = firestationRepository.findFirestationByAddress(address);
        return searchedStation;
    }


    public void updateFirestation(Firestation update) {

        Firestation existingFirestation = getFirestationByAdress(update.getAddress())
                .orElseThrow();

        int index = firestationRepository.getFirestations().indexOf(existingFirestation);
        existingFirestation.setStation(update.getStation());
        firestationRepository.getFirestations().set(index, existingFirestation);

    }

    public Firestation updateFirestation(Firestation oldFirestation, Firestation newFirestation) {
        int index = firestationRepository.getFirestations().indexOf(oldFirestation);
        firestationRepository.getFirestations().set(index, newFirestation);
        return newFirestation;
    }


}
