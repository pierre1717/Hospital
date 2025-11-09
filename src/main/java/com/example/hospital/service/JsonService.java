package com.example.hospital.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class JsonService {
    private final ObjectMapper mapper = new ObjectMapper();
    private final String filePath = "src/main/resources/data.json";

    public Map<String, Object> readData() throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            return Map.of("patients", new java.util.ArrayList<>(), "doctors", new java.util.ArrayList<>(), "appointments", new java.util.ArrayList<>());
        }
        return mapper.readValue(file, Map.class);
    }

    public void writeData(Map<String, Object> data) throws IOException {
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), data);
    }
}
