package com.api.vetgroup.dtos.create;

import com.api.vetgroup.dtos.response.RoleHistoricResponseDto;
import com.api.vetgroup.models.enums.StaffRole;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

public class StaffCreateDto {

    @NotBlank
    private String full_name;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    @Size(max = 11)
    @CPF
    private String cpf;
    @NotNull
    private StaffRole staff_role;
    private String avatar_url;
    @NotNull
    private Integer base_salary;
    private Boolean on_duty;
    @NotNull
    private Integer weekly_work_load; // in minutes
    private Integer work_load_completed; // in minutes
    private List<RoleHistoricResponseDto> role_historic;


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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public Integer getBase_salary() {
        return base_salary;
    }

    public void setBase_salary(Integer base_salary) {
        this.base_salary = base_salary;
    }

    public StaffRole getStaff_role() {
        return staff_role;
    }

    public void setStaff_role(StaffRole staff_role) {
        this.staff_role = staff_role;
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
}
