package com.openclassrooms.P5.service;

import com.openclassrooms.P5.dto.firestation.put.UpdateFirestationRequest;
import com.openclassrooms.P5.exceptions.ConflictException;
import com.openclassrooms.P5.exceptions.NotFoundException;
import com.openclassrooms.P5.model.Firestation;
import com.openclassrooms.P5.repository.FirestationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FirestationServiceImpl implements FirestationService {

    private final FirestationRepository firestationRepository;


    @Override
    public Firestation saveFirestation(Firestation firestation) {
        if(getFirestationByAdress(firestation.getAddress()).isPresent()){
            throw new ConflictException("Address already used :" + firestation.getAddress());
        }

        firestationRepository.addFirestation(firestation);
        return firestation;
    }

    @Override
    public void deleteFirestationByAddress(String address) {
        firestationRepository.deleteFirestationByAddress(address);
    }

    @Override
    public Optional<Firestation> getFirestationByAdress(String address) {
        return firestationRepository.findFirestationByAddress(address);
    }

    @Override
    public void updateFirestation(Firestation update) {

        Firestation existingFirestation = getFirestationByAdress(update.getAddress())
                .orElseThrow(() -> new NotFoundException("Firestation not found with address : " + update.getAddress()));

        int index = firestationRepository.getFirestations().indexOf(existingFirestation);
        firestationRepository.getFirestations().set(index, update);
    }


}
