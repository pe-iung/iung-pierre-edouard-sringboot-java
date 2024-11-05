package com.openclassrooms.P5.repository;

import com.openclassrooms.P5.model.Firestation;

import java.util.List;
import java.util.Optional;

public interface FirestationRepository {

    Optional<Firestation> findFirestationByAddress(String address);

    List<Firestation> getFirestationsByStation(Integer station);

    List<Firestation> getFirestations();

    Firestation addFirestation(Firestation firestation);

    boolean deleteFirestationByAddress(String address);

}
