package com.api.vetgroup.dtos.response;

import com.api.vetgroup.models.*;
import com.api.vetgroup.models.enums.StaffRole;

import java.time.LocalDateTime;
import java.util.List;

public class StaffResponseDto {

    private Long id;
    private Role role;
    private String fullName;
    private String email;
    private String avatarUrl;
    private LocalDateTime createdAt;
    private String cpf;
    private Integer baseSalary;
    private Boolean onDuty;
    private Integer weeklyWorkLoad; // in minutes
    private Integer workLoadCompleted; // in minutes
    private List<RoleHistoricResponseDto> role_historic;
    private List<RoomAccessList> access_list;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Integer getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(Integer baseSalary) {
        this.baseSalary = baseSalary;
    }

    public Boolean getOnDuty() {
        return onDuty;
    }

    public void setOnDuty(Boolean onDuty) {
        this.onDuty = onDuty;
    }

    public Integer getWeeklyWorkLoad() {
        return weeklyWorkLoad;
    }

    public void setWeeklyWorkLoad(Integer weeklyWorkLoad) {
        this.weeklyWorkLoad = weeklyWorkLoad;
    }

    public Integer getWorkLoadCompleted() {
        return workLoadCompleted;
    }

    public void setWorkLoadCompleted(Integer workLoadCompleted) {
        this.workLoadCompleted = workLoadCompleted;
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
