package com.api.vetgroup.controllers;

import com.api.vetgroup.dtos.StaffUserDto;
import com.api.vetgroup.models.Report;
import com.api.vetgroup.models.StaffUser;
import com.api.vetgroup.models.enums.StaffRole;
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

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/api/staff/v1")
public class StaffUserController {

    @Autowired
    private StaffUserService service;

    @Autowired
    private ReportService reportService;

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StaffUser> createNewStaffUser(@RequestBody @Valid StaffUserDto staffUserDto) {
        var staffUserModel = new StaffUser();
        BeanUtils.copyProperties(staffUserDto, staffUserModel); // transforma do DTO para o Model
        staffUserModel.setCreated_at(LocalDateTime.now(ZoneId.of("UTC")));
        staffUserModel.setStaffRole(staffUserDto.getStaff_role()); // transform "STAFF-ROLE" to Code of Staff-Role
        return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(staffUserModel));
    }

    @PutMapping(value = "/{id}/duty", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> setOnDutyState(@PathVariable Long id, @RequestParam(value = "on-duty", required = true) Boolean on_duty) {
        service.setOnDutyState(id, on_duty);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping(value = "/{id}/role", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> setNewRole(@PathVariable Long id, @RequestParam(value = "new-role", required = true) StaffRole new_role) {
        service.setNewRole(id, new_role);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StaffUser>> findAll() {
        List<StaffUser> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StaffUser> findById(@PathVariable Long id) {
        StaffUser obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/{id}/reports", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Report>> findReportByStaffId(@PathVariable Long id) {
        List<Report> list = reportService.findReportByStaffId(id);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
