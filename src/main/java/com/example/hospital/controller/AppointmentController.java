package com.example.hospital.controller;

import com.example.hospital.model.Appointment;
import com.example.hospital.service.JsonService;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin
public class AppointmentController {
    private final JsonService jsonService;

    public AppointmentController(JsonService jsonService) {
        this.jsonService = jsonService;
    }

    @GetMapping
    public List<Map<String, Object>> getAppointments() throws IOException {
        return (List<Map<String, Object>>) jsonService.readData().get("appointments");
    }

    @PostMapping
    public Map<String, Object> addAppointment(@RequestBody Appointment appointment) throws IOException {
        Map<String, Object> data = jsonService.readData();
        List<Map<String, Object>> appointments = (List<Map<String, Object>>) data.get("appointments");
        Map<String, Object> newAppointment = Map.of(
            "id", UUID.randomUUID().toString(),
            "patientId", appointment.getPatientId(),
            "doctorId", appointment.getDoctorId(),
            "date", appointment.getDate()
        );
        appointments.add(newAppointment);
        data.put("appointments", appointments);
        jsonService.writeData(data);
        return newAppointment;
    }
}
