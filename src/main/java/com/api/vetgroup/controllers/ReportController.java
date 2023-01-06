package com.api.vetgroup.controllers;

import com.api.vetgroup.dtos.ReportDto;
import com.api.vetgroup.models.Report;
import com.api.vetgroup.services.ReportService;
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
@RequestMapping(value = "/report")
public class ReportController {

    @Autowired
    private ReportService service;

    @PostMapping(value = "/create")
    public ResponseEntity<Object> createNewReport(@RequestBody @Valid ReportDto reportDto) {
        var reportModel = new Report();
        BeanUtils.copyProperties(reportDto, reportModel);
        reportModel.setCreated_at(LocalDateTime.now(ZoneId.of("UTC")));
        reportModel.setType(reportDto.getType());
        return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(reportModel));
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Object> updateReport(@PathVariable Long id, @RequestBody ReportDto reportDto) {
        service.requestStatus(id, reportDto.getApproved());
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
