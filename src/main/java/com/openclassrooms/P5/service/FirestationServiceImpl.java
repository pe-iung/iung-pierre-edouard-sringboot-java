package com.openclassrooms.P5.service;

import com.openclassrooms.P5.dto.FirestationDTO;
import com.openclassrooms.P5.dto.firestation.put.UpdateFirestationRequest;
import com.openclassrooms.P5.model.Firestation;
import com.openclassrooms.P5.repository.FirestationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FirestationServiceImpl implements FirestationService {

    private final FirestationRepository firestationRepository;


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
        return firestationRepository.findFirestationByAddress(address);
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

    public void updateFirestationRequestIfAdressExist(UpdateFirestationRequest firestationRequest){
        Firestation updatedFirestation = new Firestation(firestationRequest.getAddress(),firestationRequest.getStation());

        this.getFirestationByAdress(firestationRequest.getAddress())
                .map(firestation -> this.updateFirestation(firestation, updatedFirestation))
                .orElseThrow(() -> new RuntimeException("Firestation not found with address : "  + firestationRequest.getAddress() ));

    }


}
