package com.api.vetgroup.controllers;

import com.api.vetgroup.dtos.PatientDto;
import com.api.vetgroup.models.Patient;
import com.api.vetgroup.services.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/patient")
public class PatientController {

    @Autowired
    private PatientService service;

    @PostMapping(value = "/create")
    public ResponseEntity<Object> createNewPatient(@RequestBody @Valid PatientDto patientDto) {
        var patientModel = new Patient();
        BeanUtils.copyProperties(patientDto, patientModel);
        patientModel.setCreated_at(LocalDateTime.now(ZoneId.of("UTC")));
        patientModel.setSity(patientDto.getSity());
        patientModel.setStatus(patientDto.getStatus());
        patientModel.setKind(patientDto.getKind());
        return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(patientModel));
    }

    @GetMapping
    public ResponseEntity<List<Patient>> findAll() {
        List<Patient> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
}
