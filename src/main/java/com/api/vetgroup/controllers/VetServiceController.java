package com.api.vetgroup.controllers;

import com.api.vetgroup.dtos.create.ServiceCreateDto;
import com.api.vetgroup.dtos.UpdateDescriptionDto;
import com.api.vetgroup.dtos.response.ServiceResponseDto;
import com.api.vetgroup.models.VetService;
import com.api.vetgroup.models.enums.ServiceStatus;
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
import java.util.Objects;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/api/services/v1")
public class VetServiceController {

    @Autowired
    private VetServiceService service;

    @Autowired
    private ServiceMapper mapper;

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createNewService(@RequestBody @Valid ServiceCreateDto serviceCreateDto) {
        serviceCreateDto.setCreatedAt(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
        VetService serviceModel = mapper.convertDtoToService(serviceCreateDto);

        service.insert(serviceModel);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping(value = "/{id}", params = "status", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> changeStatus(@PathVariable Long id, @RequestParam(value = "status", required = true) ServiceStatus status) {
        try {
            service.changeStatus(id, status);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (IllegalAccessException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateDescription(@PathVariable Long id,
                                                    @RequestBody(required = true) UpdateDescriptionDto descriptionDto)
    {
        try {
            service.updateDescription(id, descriptionDto.getStaff_id(), descriptionDto.getDescription());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ServiceResponseDto>> findAll(
            @RequestParam(value = "sort_by", required = false, defaultValue = "id") String sort_by,
            @RequestParam(value = "direction", required = false, defaultValue = "ASC") String direction
        )
    {
        List<VetService> list = service.findAll(sort_by, direction);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.convertListToDto(list));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ServiceResponseDto> findById(@PathVariable Long id) {
        VetService obj = service.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.convertServiceToDto(obj));
    }

    @GetMapping(params = "patient-id", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ServiceResponseDto>> findPatientServices(@RequestParam(value = "patient-id") Long patient_id) {
        List<VetService> list = service.findServicesByPatientId(patient_id);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.convertListToDto(list));
    }

    @GetMapping(params = "staff-id", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ServiceResponseDto>> findServiceByStaffId(@RequestParam(value = "staff-id") Long staff_id) {
        List<VetService> list = service.findServicesByStaffId(staff_id);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.convertListToDto(list));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteService(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
