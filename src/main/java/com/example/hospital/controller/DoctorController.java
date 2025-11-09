package com.example.hospital.controller;

import com.example.hospital.model.Doctor;
import com.example.hospital.service.JsonService;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/api/doctors")
@CrossOrigin
public class DoctorController {
    private final JsonService jsonService;

    public DoctorController(JsonService jsonService) {
        this.jsonService = jsonService;
    }

    @GetMapping
    public List<Map<String, Object>> getDoctors() throws IOException {
        return (List<Map<String, Object>>) jsonService.readData().get("doctors");
    }

    @PostMapping
    public Map<String, Object> addDoctor(@RequestBody Doctor doctor) throws IOException {
        Map<String, Object> data = jsonService.readData();
        List<Map<String, Object>> doctors = (List<Map<String, Object>>) data.get("doctors");
        Map<String, Object> newDoctor = Map.of("id", UUID.randomUUID().toString(), "name", doctor.getName(), "specialty", doctor.getSpecialty());
        doctors.add(newDoctor);
        data.put("doctors", doctors);
        jsonService.writeData(data);
        return newDoctor;
    }
}
