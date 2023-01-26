package com.api.vetgroup.controllers;

import com.api.vetgroup.dtos.ServiceCreateDto;
import com.api.vetgroup.dtos.response.ServiceResponseDto;
import com.api.vetgroup.dtos.ServiceStatusDto;
import com.api.vetgroup.models.VetService;
import com.api.vetgroup.services.VetServiceService;
import com.api.vetgroup.services.customMappers.ServiceMapper;
import jakarta.validation.Valid;
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
@RequestMapping(value = "/api/services/v1")
public class VetServiceController {

    @Autowired
    private VetServiceService service;

    @Autowired
    private ServiceMapper serviceMapper;

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createNewService(@RequestBody @Valid ServiceCreateDto serviceCreateDto) {
        serviceCreateDto.setCreated_at(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));

        VetService serviceModel = serviceMapper.convertDtoToService(serviceCreateDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(serviceModel));
    }

    @PatchMapping(value = "/{id}/status", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> changeStatus(@PathVariable Long id, @RequestBody ServiceStatusDto statusDto) {
        try {
            service.changeStatus(id, statusDto.getStatus());

            return ResponseEntity.noContent().build();
        } catch (IllegalAccessException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VetService>> findAll() {
        List<VetService> list = service.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ServiceResponseDto> findById(@PathVariable Long id) {
        VetService obj = service.findById(id);
        ServiceResponseDto serviceDto = serviceMapper.convertServiceToDto(obj);
        return ResponseEntity.status(HttpStatus.OK).body(serviceDto);
    }

    @GetMapping(value = "/patient", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VetService>> findPatientServices(@RequestParam(value = "id") Long patient_id) {
        List<VetService> list = service.findServicesByPatientId(patient_id);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping(value = "/staff", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VetService>> findServiceByStaffId(@RequestParam(value = "id") Long staff_id) {
        List<VetService> list = service.findServicesByStaffId(staff_id);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteService(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
