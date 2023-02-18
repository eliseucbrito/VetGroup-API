package com.api.vetgroup.dtos.response;

import com.api.vetgroup.dtos.StaffReducedDto;
import com.api.vetgroup.models.enums.StaffRole;

import java.time.LocalDateTime;

public class RoleHistoricResponseDto {

    private LocalDateTime startedIn;
    private String role;
    private Integer baseSalary;
    private Integer weeklyWorkLoad;
    private StaffReducedDto promoter;

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

    public StaffReducedDto getPromoter() {
        return promoter;
    }

    public void setPromoter(StaffReducedDto promoter) {
        this.promoter = promoter;
    }
}
