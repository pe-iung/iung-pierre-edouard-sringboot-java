package com.openclassrooms.P5;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.asm.TypeReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

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

        System.out.println(jsonDataFile);


    }
}
