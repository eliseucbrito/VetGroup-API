package com.api.vetgroup.dtos.create;

import com.api.vetgroup.models.enums.StaffRole;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class RoleHistoricCreateDto {

    private LocalDateTime startedIn;

    @NotNull
    private String role;

    @NotNull
    private Integer baseSalary;

    @NotNull
    private Integer weeklyWorkLoad;

    public LocalDateTime getStartedIn() {
        return startedIn;
    }

    public void setStartedIn(LocalDateTime startedIn) {
        this.startedIn = startedIn;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(Integer baseSalary) {
        this.baseSalary = baseSalary;
    }

    public Integer getWeeklyWorkLoad() {
        return weeklyWorkLoad;
    }

    public void setWeeklyWorkLoad(Integer weeklyWorkLoad) {
        this.weeklyWorkLoad = weeklyWorkLoad;
    }
}
