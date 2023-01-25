package com.api.vetgroup.controllers;

import com.api.vetgroup.dtos.RoleHistoricCreateDto;
import com.api.vetgroup.dtos.StaffCreateDto;
import com.api.vetgroup.dtos.StaffResponseDto;
import com.api.vetgroup.models.Report;
import com.api.vetgroup.models.RoleHistoric;
import com.api.vetgroup.models.StaffUser;
import com.api.vetgroup.services.ReportService;
import com.api.vetgroup.services.RoleHistoricService;
import com.api.vetgroup.services.StaffUserService;
import com.api.vetgroup.services.customMappers.RoleHistoricMapper;
import com.api.vetgroup.services.customMappers.StaffMapper;
import jakarta.validation.Valid;
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

    @Autowired
    private RoleHistoricService roleHistoricService;

    @Autowired
    private StaffMapper mapper;

    @Autowired
    private RoleHistoricMapper roleHistoricMapper;


    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StaffUser> createNewStaffUser(@RequestBody @Valid StaffCreateDto staffDto) {
        StaffUser staffModel = mapper.convertDtoToStaff(staffDto);

        staffModel.setCreated_at(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
        return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(staffModel));
    }

    @PutMapping(value = "/{id}/duty", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> setOnDutyState(@PathVariable Long id, @RequestParam(value = "on-duty", required = true) Boolean on_duty) {
        service.setOnDutyState(id, on_duty);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping(value = "/{id}/new-role", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> setNewRole(@PathVariable Long id, @RequestBody @Valid RoleHistoricCreateDto roleHistoricCreateDto) {
        try {
            roleHistoricCreateDto.setStarted_in(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
            RoleHistoric roleHistoric = roleHistoricMapper.convertToRoleHistoric(roleHistoricCreateDto, id);

            service.setNewRole(roleHistoric);
            roleHistoricService.insert(roleHistoric);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StaffUser>> findAll() {
        List<StaffUser> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StaffResponseDto> findById(@PathVariable Long id) {
        StaffUser staff = service.findById(id);
        StaffResponseDto staffDto = mapper.convertStaffToDto(staff);
        return ResponseEntity.ok().body(staffDto);
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
