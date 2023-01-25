package com.api.vetgroup.controllers;

import com.api.vetgroup.dtos.ReportDto;
import com.api.vetgroup.models.Report;
import com.api.vetgroup.models.StaffUser;
import com.api.vetgroup.models.enums.ReportTypes;
import com.api.vetgroup.services.ReportService;
import com.api.vetgroup.services.StaffUserService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/api/reports/v1")
public class ReportController {

    @Autowired
    private ReportService service;

    @Autowired
    private StaffUserService staffService;

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createNewReport(@RequestBody @Valid ReportDto reportDto) {
        try {
            var reportModel = new Report();
            StaffUser staff = staffService.findById(reportDto.getStaff_id());
            BeanUtils.copyProperties(reportDto, reportModel);
            reportModel.setCreated_at(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
            reportModel.setStaff(staff);
            reportModel.setType(reportDto.getType());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(reportModel));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateApproved(
            @PathVariable Long id,
            @RequestParam(value = "approved", required = true) boolean approved)
    {
        try {
            service.setApprovedReport(id, approved);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Report>> findAll() {
        List<Report> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value  = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Report> findById(@PathVariable Long id) {
        Report obj = service.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(obj);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteReport(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
