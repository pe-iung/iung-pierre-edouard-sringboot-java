package com.openclassrooms.P5.repository;

import com.openclassrooms.P5.model.Firestation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class FirestationRepositoryFromJson implements FirestationRepository{

    private final DataLoader dataLoader;

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
    public void addFirestation(Firestation firestation) {
        dataLoader.getFirestations().add(firestation);
    }

    @Override
    public void deleteFirestationByAddress(String address) {
        List<Firestation> firestations = dataLoader.getFirestations();
        boolean removed = firestations.removeIf(firestation -> firestation.getAddress().equals(address));
        if (removed) {
            log.info("Firestation removed at address: {}", address);
        } else {
            log.warn("No firestation found at address: {}", address);
        }
    }

}
