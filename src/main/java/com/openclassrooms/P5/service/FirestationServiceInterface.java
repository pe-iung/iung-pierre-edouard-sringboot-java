package com.openclassrooms.P5.service;
import com.openclassrooms.P5.dto.FirestationDTO;
import com.openclassrooms.P5.model.Firestation;

import java.util.List;
import java.util.Optional;

public interface FirestationServiceInterface {
    // public List<FirestationDTO> getAllFirestationDTO();
    public Firestation saveFirestation(Firestation firestation);

    public void deleteFirestationByAddress(String address);

    public Optional<Firestation> getFirestationByAdress(String address);

    public void updateFirestation(Firestation update);

    public Firestation updateFirestation(Firestation oldFirestation, Firestation newFirestation);
}
