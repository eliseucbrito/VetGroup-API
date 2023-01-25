package com.api.vetgroup.dtos;

import com.api.vetgroup.models.*;
import com.api.vetgroup.models.enums.StaffRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StaffResponseDto {

    private Long id;
    private StaffRole staff_role;
    private String full_name;
    private String email;
    private String avatar_url;
    private LocalDateTime created_at;
    private String cpf;
    private Integer base_salary;
    private Boolean on_duty;
    private Integer weekly_work_load; // in minutes
    private Integer work_load_completed; // in minutes
    private List<RoleHistoricResponseDto> role_historic;
    private List<RoomAccessList> access_list;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StaffRole getStaff_role() {
        return staff_role;
    }

    public void setStaff_role(StaffRole staff_role) {
        this.staff_role = staff_role;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Integer getBase_salary() {
        return base_salary;
    }

    public void setBase_salary(Integer base_salary) {
        this.base_salary = base_salary;
    }

    public Boolean getOn_duty() {
        return on_duty;
    }

    public void setOn_duty(Boolean on_duty) {
        this.on_duty = on_duty;
    }

    public Integer getWeekly_work_load() {
        return weekly_work_load;
    }

    public void setWeekly_work_load(Integer weekly_work_load) {
        this.weekly_work_load = weekly_work_load;
    }

    public Integer getWork_load_completed() {
        return work_load_completed;
    }

    public void setWork_load_completed(Integer work_load_completed) {
        this.work_load_completed = work_load_completed;
    }

    public List<RoleHistoricResponseDto> getRole_historic() {
        return role_historic;
    }

    public void setRole_historic(List<RoleHistoricResponseDto> role_historic) {
        this.role_historic = role_historic;
    }

    public List<RoomAccessList> getAccess_list() {
        return access_list;
    }

    public void setAccess_list(List<RoomAccessList> access_list) {
        this.access_list = access_list;
    }
}
