package com.api.vetgroup.dtos.create;

import com.api.vetgroup.models.enums.StaffRole;

import java.time.LocalDateTime;

public class RoleHistoricCreateDto {

    private LocalDateTime started_in;
    private String role;
    private Integer base_salary;
    private Integer weekly_work_load;
    private Long promoted_by;
    private Long staff_id;

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

    public Long getPromoted_by() {
        return promoted_by;
    }

    public void setPromoted_by(Long promoted_by) {
        this.promoted_by = promoted_by;
    }

    public Long getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(Long staff_id) {
        this.staff_id = staff_id;
    }
}
