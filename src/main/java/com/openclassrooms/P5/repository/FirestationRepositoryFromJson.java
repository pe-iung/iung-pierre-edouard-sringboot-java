package com.openclassrooms.P5.repository;

import com.openclassrooms.P5.model.Firestation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FirestationRepositoryFromJson implements FirestationRepository{

    private final DataLoader dataLoader;
    //private List<Firestation> firestations = new ArrayList<>();
    public Firestation getFirestationByAddress(String address) {

        return findFirestationByAddress(address).orElseThrow(() -> new RuntimeException("Not found"));
    }

    @Override
    public Optional<Firestation> findFirestationByAddress(String address) {
        return this.getFirestations()
                .stream()
                .filter(f -> f.getAddress().equals(address))
                .findFirst();
    }

    @Override
    public List<Firestation> getFirestationsByStation(Integer station) {
        return this.getFirestations()
                .stream()
                .filter(f -> f.getStation() == station)
                .toList();
    }

    @Override
    public List<Firestation> getFirestations() {
        return this.dataLoader.getFirestations();
    }

    @Override
    public Firestation addFirestation(Firestation firestation) {
        dataLoader.addFirestation(firestation);
        return firestation;
    }

    @Override
    public boolean deleteFirestationByAddress(String address) {
        return dataLoader.deleteFirestationByAddress(address); // Delete from DataLoader
    }

}
