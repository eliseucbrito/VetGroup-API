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

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createNewReport(@RequestBody @Valid ReportCreateDto reportCreateDto) {
        try {
            reportCreateDto.setCreatedAt(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
            Report reportModel = mapper.convertDtoToReport(reportCreateDto);
            service.insert(reportModel);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PatchMapping(value = "/{id}", params = "approved", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateApproved(
            @PathVariable Long id,
            @RequestParam(value = "approved", required = true) Boolean approved)
    {
        try {
            service.setApprovedReport(id, approved);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity findAll(
            @RequestParam(value = "sort_by", required = false, defaultValue = "id") String sort_by,
            @RequestParam(value = "direction", required = false, defaultValue = "ASC") String direction
        )
    {
        try {
            List<Report> list = service.findAll(sort_by, direction);
            return ResponseEntity.ok().body(mapper.convertListToDto(list));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping(value  = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity findById(@PathVariable Long id) {
        try {
            Report report = service.findById(id);
            ReportResponseDto reportDto = mapper.convertReportToDto(report);

            return ResponseEntity.status(HttpStatus.OK).body(reportDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
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
