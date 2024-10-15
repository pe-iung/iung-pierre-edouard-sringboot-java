package com.openclassrooms.P5.repository;

import com.openclassrooms.P5.model.Firestation;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface FirestationRepository {

    Optional<Firestation> findFirestationByAddress(String address);

    List<Firestation> getFirestationsByStation(Integer station);

    List<Firestation> getFirestations();

}