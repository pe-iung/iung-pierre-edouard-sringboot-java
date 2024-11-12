package com.openclassrooms.P5.service;
import com.openclassrooms.P5.model.Firestation;

import java.util.Optional;

public interface FirestationService {
    // public List<FirestationDTO> getAllFirestationDTO();
    public Firestation saveFirestation(Firestation firestation);

    public void deleteFirestationByAddress(String address);

    public Optional<Firestation> getFirestationByAdress(String address);

    public void updateFirestation(Firestation update);

    public Firestation updateFirestation(Firestation oldFirestation, Firestation newFirestation);
}
