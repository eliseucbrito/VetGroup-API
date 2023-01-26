package com.api.vetgroup.controllers;

import com.api.vetgroup.dtos.create.ReportCreateDto;
import com.api.vetgroup.dtos.response.ReportResponseDto;
import com.api.vetgroup.models.Report;
import com.api.vetgroup.services.ReportService;
import com.api.vetgroup.services.StaffUserService;
import com.api.vetgroup.services.customMappers.ReportMapper;
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
@RequestMapping(value = "/api/reports/v1")
public class ReportController {

    @Autowired
    private ReportService service;

    @Autowired
    private ReportMapper mapper;

    @Autowired
    private StaffUserService staffService;

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createNewReport(@RequestBody @Valid ReportCreateDto reportCreateDto) {
        try {
            reportCreateDto.setCreated_at(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
            Report reportModel = mapper.convertDtoToReport(reportCreateDto);
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
    public ResponseEntity<List<ReportResponseDto>> findAll() {
        List<Report> list = service.findAll();
        return ResponseEntity.ok().body(mapper.convertListToDto(list));
    }

    @GetMapping(value  = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReportResponseDto> findById(@PathVariable Long id) {
        Report report = service.findById(id);
        ReportResponseDto reportDto = mapper.convertReportToDto(report);

        return ResponseEntity.status(HttpStatus.OK).body(reportDto);
    }

    @GetMapping(params = "staff-id", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ReportResponseDto>> findReportByStaffId(@RequestParam(value = "staff-id", required = true) Long staff_id) {
        List<Report> list = service.findReportByStaffId(staff_id);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.convertListToDto(list));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteReport(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
