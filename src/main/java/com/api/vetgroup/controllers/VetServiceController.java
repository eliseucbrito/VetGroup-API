package com.api.vetgroup.controllers;

import com.api.vetgroup.dtos.ReportDto;
import com.api.vetgroup.dtos.VetServiceDto;
import com.api.vetgroup.models.Patient;
import com.api.vetgroup.models.Report;
import com.api.vetgroup.models.StaffUser;
import com.api.vetgroup.models.VetService;
import com.api.vetgroup.models.enums.ReportTypes;
import com.api.vetgroup.models.enums.ServiceStatus;
import com.api.vetgroup.models.enums.ServiceTypes;
import com.api.vetgroup.services.PatientService;
import com.api.vetgroup.services.StaffUserService;
import com.api.vetgroup.services.VetServiceService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/service")
public class VetServiceController {

    @Autowired
    private VetServiceService service;

    @Autowired
    private StaffUserService staffService;

    @Autowired
    private PatientService patientService;

    @PostMapping(value = "/create")
    public ResponseEntity<Object> createNewService(@RequestBody @Valid VetServiceDto vetServiceDto) {
        var serviceModel = new VetService();
        StaffUser staff = staffService.findById(vetServiceDto.getStaff_id());
        Patient patient = patientService.findById(vetServiceDto.getPatient_id());
        BeanUtils.copyProperties(vetServiceDto, serviceModel);
        serviceModel.setCreated_at(LocalDateTime.now(ZoneId.of("UTC")));
        serviceModel.setStaff(staff);
        serviceModel.setPatient(patient);
        serviceModel.setType(vetServiceDto.getType());
        if (vetServiceDto.getType() == ServiceTypes.EMERGENCY) {
            serviceModel.setStatus(ServiceStatus.WAITING_PAYMENT);
        }
        serviceModel.setStatus(vetServiceDto.getStatus());
        return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(serviceModel));
    }

    @PatchMapping(value = "/{id}/status")
    public ResponseEntity<Object> changeStatus(@PathVariable Long id, @RequestBody VetServiceDto vetServiceDto) {
        VetService vetService = service.findById(id);

        if (vetService.getType() == ServiceTypes.EMERGENCY && vetServiceDto.getStatus() == ServiceStatus.SCHEDULED) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Service of EMERGENCY not accept the status SCHEDULED");
        }

        if (vetService.getStatus() == vetServiceDto.getStatus()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This service is already "+vetService.getStatus());
        }

        if (vetService.getStatus() == ServiceStatus.PAID) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This service cant be "+vetServiceDto.getStatus()+ " because it is already PAID");
        }

        if (vetServiceDto.getStatus() == ServiceStatus.PAID) {
            if (vetService.getPrice() == null || vetService.getPrice() == 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This service does not have an acceptable price");
            }
        }

        try {
            service.changeStatus(vetService, vetServiceDto.getStatus());
            return ResponseEntity.noContent().build();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<VetService>> findAll() {
        List<VetService> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<VetService> findById(@PathVariable Long id) {
        VetService obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
