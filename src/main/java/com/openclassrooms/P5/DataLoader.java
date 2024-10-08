package com.openclassrooms.P5;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.asm.TypeReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Component
public class DataLoader implements CommandLineRunner {

    private final ObjectMapper objectMapper;

    public DataLoader(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    //private final PersonRepository;

    @Override
    public void run(String... args) throws Exception {

        JsonNode jsonDataFile;

        try (InputStream inputStream = TypeReference.class.getResourceAsStream("/data/data.json")) {
            jsonDataFile = objectMapper.readValue(inputStream, JsonNode.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON data", e);
        }

//        JsonNode persons = getPersons(jsonDataFile);
//        JsonNode firestations = getFirestations(jsonDataFile);
//        JsonNode medicalrecords = getMedicalrecords(jsonDataFile);
//        System.out.println(medicalrecords);



    }
    private JsonNode getPersons(JsonNode jsonDataFile) {
        return Optional.ofNullable(jsonDataFile)
                .map(j -> j.get("persons"))
                .orElseThrow(() -> new IllegalArgumentException("Invalid JSON Object"));
    }

    private JsonNode getFirestations(JsonNode jsonDataFile) {
        return Optional.ofNullable(jsonDataFile)
                .map(j -> j.get("firestations"))
                .orElseThrow(() -> new IllegalArgumentException("Invalid JSON Object"));
    }

    private JsonNode getMedicalrecords(JsonNode jsonDataFile) {
        return Optional.ofNullable(jsonDataFile)
                .map(j -> j.get("medicalrecords"))
                .orElseThrow(() -> new IllegalArgumentException("Invalid JSON Object"));
    }

}
