package com.openclassrooms.P5.repository;

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
public class DataLoader {

    private final DataStorage dataStorage;


    public DataLoader(ObjectMapper objectMapper) throws IOException {
        InputStream inputStream = TypeReference.class.getResourceAsStream("/data/data.json");

        dataStorage = objectMapper.readValue(inputStream, DataStorage.class);

        log.info("Data loaded");
        log.debug(String.valueOf(dataStorage));
    }

    public List<Person> getPersons() {
        return dataStorage.getPersons();
    }

    public List<Firestation> getFirestations() {
        return dataStorage.getFirestations();
    }

    public List<MedicalRecord> getMedicalRecords() {
        return dataStorage.getMedicalrecords();
    }


    @Data
    static class DataStorage {
        List<Person> persons;
        List<Firestation> firestations;
        List<MedicalRecord> medicalrecords;
    }


}
