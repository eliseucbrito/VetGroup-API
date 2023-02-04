package com.api.vetgroup.dtos.response;

import com.api.vetgroup.dtos.StaffReducedDto;
import com.api.vetgroup.models.enums.StaffRole;

import java.time.LocalDateTime;

public class RoleHistoricResponseDto {

    private LocalDateTime started_in;
    private String role;
    private Integer base_salary;
    private Integer weekly_work_load;
    private StaffReducedDto promoter;

    public LocalDateTime getStarted_in() {
        return started_in;
    }

    public void setStarted_in(LocalDateTime started_in) {
        this.started_in = started_in;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getBase_salary() {
        return base_salary;
    }

    public void setBase_salary(Integer base_salary) {
        this.base_salary = base_salary;
    }

    public Integer getWeekly_work_load() {
        return weekly_work_load;
    }

    public void setWeekly_work_load(Integer weekly_work_load) {
        this.weekly_work_load = weekly_work_load;
    }

    public StaffReducedDto getPromoter() {
        return promoter;
    }

    public void setPromoter(StaffReducedDto promoter) {
        this.promoter = promoter;
    }

}
