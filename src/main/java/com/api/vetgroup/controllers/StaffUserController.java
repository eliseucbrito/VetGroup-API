package com.api.vetgroup.controllers;

import com.api.vetgroup.dtos.create.RoleHistoricCreateDto;
import com.api.vetgroup.dtos.create.StaffCreateDto;
import com.api.vetgroup.dtos.response.StaffResponseDto;
import com.api.vetgroup.models.Report;
import com.api.vetgroup.models.RoleHistoric;
import com.api.vetgroup.models.StaffUser;
import com.api.vetgroup.services.ReportService;
import com.api.vetgroup.services.RoleHistoricService;
import com.api.vetgroup.services.StaffUserService;
import com.api.vetgroup.services.customMappers.RoleHistoricMapper;
import com.api.vetgroup.services.customMappers.StaffMapper;
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
@RequestMapping(value = "/api/staff/v1")
public class StaffUserController {

    @Autowired
    private StaffUserService service;
    @Autowired
    private RoleHistoricService roleHistoricService;
    @Autowired
    private StaffMapper mapper;
    @Autowired
    private RoleHistoricMapper roleHistoricMapper;

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createNewStaffUser(@RequestBody @Valid StaffCreateDto staffDto) {
        StaffUser staffModel = mapper.convertDtoToStaff(staffDto);
        service.insert(staffModel);
        staffModel.setCreated_at(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping(value = "/{id}", params = "on-duty" ,consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> setOnDutyState(@PathVariable Long id, @RequestParam(value = "on-duty", required = true) Boolean on_duty) {
        service.setOnDutyState(id, on_duty);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
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
    public ResponseEntity<List<StaffResponseDto>> findAll() {
        List<StaffUser> list = service.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(mapper.convertListToDto(list));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StaffResponseDto> findById(@PathVariable Long id) {
        StaffUser staff = service.findById(id);
        StaffResponseDto staffDto = mapper.convertStaffToDto(staff);
        return ResponseEntity.status(HttpStatus.OK).body(staffDto);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
