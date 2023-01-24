package com.api.vetgroup.dtos;

import com.api.vetgroup.models.enums.StaffRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class RoleHistoricDto {

    private LocalDateTime started_in;
    private StaffRole role;
    private Integer base_salary;
    private Integer weekly_work_load;
    private Long promoted_by;
    private Long staff;

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

    public Long getPromoted_by() {
        return promoted_by;
    }

    public void setPromoted_by(Long promoted_by) {
        this.promoted_by = promoted_by;
    }

    public Long getStaff() {
        return staff;
    }

    public void setStaff(Long staff) {
        this.staff = staff;
    }
}
