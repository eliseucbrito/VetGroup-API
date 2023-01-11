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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/reports")
public class ReportController {

    @Autowired
    private ReportService service;

    @Autowired
    private StaffUserService staffService;

    @PostMapping(value = "/create")
    public ResponseEntity<Object> createNewReport(@RequestBody @Valid ReportDto reportDto) {
        var reportModel = new Report();
        StaffUser staff = staffService.findById(reportDto.getStaff_id());
        BeanUtils.copyProperties(reportDto, reportModel);
        reportModel.setCreated_at(LocalDateTime.now(ZoneId.of("UTC")));
        reportModel.setStaff(staff);
        reportModel.setType(reportDto.getType());
        return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(reportModel));
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Object> updateApproved(
            @PathVariable Long id,
            @RequestParam(value = "approved", required = true, defaultValue = "false") boolean approved)
    {
        try {
            Report report = service.findById(id);
            service.requestStatus(report, approved);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Report>> findAll() {
        List<Report> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
