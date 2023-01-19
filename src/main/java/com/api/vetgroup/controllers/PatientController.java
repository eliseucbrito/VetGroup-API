package com.api.vetgroup.controllers;

import com.api.vetgroup.dtos.PatientDto;
import com.api.vetgroup.models.Patient;
import com.api.vetgroup.services.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/api/patients/v1")
public class PatientController {

    @Autowired
    private PatientService service;

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createNewPatient(@RequestBody @Valid PatientDto patientDto) {
        var patientModel = new Patient();
        BeanUtils.copyProperties(patientDto, patientModel);
        patientModel.setCreated_at(LocalDateTime.now(ZoneId.of("UTC")));
        patientModel.setKind(patientDto.getKind());
        patientModel.setBirth_date(patientDto.getBirth_date());
        return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(patientModel));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Patient>> findAll() {
        List<Patient> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Patient> findByID(@PathVariable Long id) {
        Patient obj = service.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(obj);
    }

}
