package com.api.vetgroup.dtos;

import com.api.vetgroup.models.enums.StaffRole;

import java.time.LocalDateTime;

public class RoleHistoricResponseDto {

    private LocalDateTime started_in;
    private StaffRole role;
    private Integer base_salary;
    private Integer weekly_work_load;
    private PromoterDto promoter;

    public LocalDateTime getStarted_in() {
        return started_in;
    }

    public void setStarted_in(LocalDateTime started_in) {
        this.started_in = started_in;
    }

    public StaffRole getRole() {
        return role;
    }

    public void setRole(StaffRole role) {
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

    public PromoterDto getPromoter() {
        return promoter;
    }

    public void setPromoter(PromoterDto promoter) {
        this.promoter = promoter;
    }
}
