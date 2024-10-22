package com.openclassrooms.P5.repository;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.P5.model.Firestation;
import com.openclassrooms.P5.model.MedicalRecord;
import com.openclassrooms.P5.model.Person;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.asm.TypeReference;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Slf4j
@Component
//@JsonFormat(pattern = "MM/dd/yyyy")
public class DataLoader {

    private final DataStorage dataStorage;


    public DataLoader(ObjectMapper objectMapper) throws IOException {
       InputStream inputStream = TypeReference.class.getResourceAsStream("/data/data.json");

       dataStorage = objectMapper.readValue(inputStream, DataStorage.class);

       log.info("Data loaded");
       log.debug(String.valueOf(dataStorage));
    }

    public List<Person> getPersons(){
        return dataStorage.getPersons();
    }
    public List<Firestation> getFirestations(){
        return dataStorage.getFirestations();
    }
    public List<MedicalRecord> getMedicalRecords(){
        return dataStorage.getMedicalrecords();
    }

    public void addFirestation(Firestation firestation) {
        dataStorage.getFirestations().add(firestation);
        log.info("New firestation added: {}", firestation);
    }

    public boolean deleteFirestationByAddress(String address) {
        List<Firestation> firestations = dataStorage.getFirestations();
        boolean removed = firestations.removeIf(firestation -> firestation.getAddress().equals(address));
        if (removed) {
            log.info("Firestation removed at address: {}", address);
        } else {
            log.warn("No firestation found at address: {}", address);
        }
        return removed;
    }

    public void addMedicalRecord(MedicalRecord medicalRecord) {
        dataStorage.getMedicalrecords().add(medicalRecord);
        log.info("New Medicalrecord added: {}", medicalRecord);
    }

    public boolean deleteMedicalRecordById(String id) {
        List<MedicalRecord> medicalRecords = dataStorage.getMedicalrecords();
        boolean removed = medicalRecords.removeIf(
                medicalRecord -> medicalRecord.getId().equals(id));
        if (removed) {
            log.info("Medical record removed for id = first-lastname: {}", id);
        } else {
            log.warn("No medical record found for id = first-lastname: {}", id);
        }
        return removed;
    }

    public void addPerson(Person person) {
        dataStorage.getPersons().add(person);
        log.info("New person added: {}", person);
    }

    public boolean deletePersonById(String id) {
        List<Person> persons = dataStorage.getPersons();
        boolean removed = persons.removeIf(person -> person.getId().equals(id));
        if (removed) {
            log.info("Person removed with id= firstname-lastname: {}", id);
        } else {
            log.warn("No person found with id= firstname-lastname: {}", id);
        }
        return removed;
    }


    @Data
    static class DataStorage {
        List<Person> persons;
        List<Firestation> firestations;
        List<MedicalRecord> medicalrecords;
    }


}
