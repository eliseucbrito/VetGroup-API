package com.api.vetgroup.controllers;

import com.api.vetgroup.dtos.ReportDto;
import com.api.vetgroup.dtos.VetServiceDto;
import com.api.vetgroup.models.Report;
import com.api.vetgroup.models.VetService;
import com.api.vetgroup.models.enums.ReportTypes;
import com.api.vetgroup.models.enums.ServiceStatus;
import com.api.vetgroup.services.VetServiceService;
import jakarta.validation.Valid;
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

    @PostMapping(value = "/create")
    public ResponseEntity<Object> createNewService(@RequestBody @Valid VetServiceDto vetServiceDto) {
        var serviceModel = new VetService();
        BeanUtils.copyProperties(vetServiceDto, serviceModel);
        serviceModel.setCreated_at(LocalDateTime.now(ZoneId.of("UTC")));
        serviceModel.setType(vetServiceDto.getType());
        serviceModel.setStatus(vetServiceDto.getStatus());
        return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(serviceModel));
    }

    @PatchMapping(value = "/{id}/status")
    public ResponseEntity<Object> changeStatus(@PathVariable Long id, @RequestBody VetServiceDto vetServiceDto) {
//        VetService vetService = service.findById(id);
        try {
            service.changeStatus(id, vetServiceDto.getStatus());
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
