package com.example.hospital.controller;

import com.example.hospital.model.Patient;
import com.example.hospital.service.JsonService;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/api/patients")
@CrossOrigin
public class PatientController {
    private final JsonService jsonService;

    public PatientController(JsonService jsonService) {
        this.jsonService = jsonService;
    }

    @GetMapping
    public List<Map<String, Object>> getPatients() throws IOException {
        return (List<Map<String, Object>>) jsonService.readData().get("patients");
    }

    @PostMapping
    public Map<String, Object> addPatient(@RequestBody Patient patient) throws IOException {
        Map<String, Object> data = jsonService.readData();
        List<Map<String, Object>> patients = (List<Map<String, Object>>) data.get("patients");
        Map<String, Object> newPatient = Map.of("id", UUID.randomUUID().toString(), "name", patient.getName(), "age", patient.getAge());
        patients.add(newPatient);
        data.put("patients", patients);
        jsonService.writeData(data);
        return newPatient;
    }
}
