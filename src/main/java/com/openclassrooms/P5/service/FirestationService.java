package com.openclassrooms.P5.service;
import com.openclassrooms.P5.model.Firestation;

import java.util.Optional;

public interface FirestationService {

    Firestation saveFirestation(Firestation firestation);

    void deleteFirestationByAddress(String address);

    Optional<Firestation> getFirestationByAdress(String address);

    void updateFirestation(Firestation update);

}
