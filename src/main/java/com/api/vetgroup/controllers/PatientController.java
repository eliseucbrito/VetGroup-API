package com.api.vetgroup.controllers;

import com.api.vetgroup.dtos.PatientCreateDto;
import com.api.vetgroup.dtos.PatientResponseDto;
import com.api.vetgroup.models.Patient;
import com.api.vetgroup.models.VetService;
import com.api.vetgroup.services.PatientService;
import com.api.vetgroup.services.customMappers.PatientMapper;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/api/patients/v1")
public class PatientController {

    @Autowired
    private PatientService service;

    @Autowired
    private PatientMapper mapper;

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createNewPatient(@RequestBody @Valid PatientCreateDto patientCreateDto) {
        patientCreateDto.setCreated_at(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
        Patient patientModel = mapper.convertDtoToPatient(patientCreateDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(patientModel));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Patient>> findAll() {
        List<Patient> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PatientResponseDto> findByID(@PathVariable Long id) {
        Patient patient = service.findById(id);
        PatientResponseDto patientDto = mapper.convertPatientToDto(patient);

        return ResponseEntity.status(HttpStatus.OK).body(patientDto);
    }

    @GetMapping(value = "/{id}/details", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VetService>> patientDetails(@PathVariable Long id) {
        Patient obj = service.findById(id);
        List<VetService> services = obj.getService();
        return ResponseEntity.status(HttpStatus.OK).body(services);
    }

}
