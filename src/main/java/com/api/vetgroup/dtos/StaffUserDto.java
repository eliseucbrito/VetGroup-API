package com.api.vetgroup.dtos;

import com.api.vetgroup.models.enums.StaffRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;

public class StaffUserDto {

    @NotBlank
    private String full_name;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    @Size(max = 11)
    private String cpf;
    private StaffRole staff_role;
    private String avatar_url;
    private Double base_salary;


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

    public Double getBase_salary() {
        return base_salary;
    }

    public void setBase_salary(Double base_salary) {
        this.base_salary = base_salary;
    }

    public StaffRole getStaff_role() {
        return staff_role;
    }

    public void setStaff_role(StaffRole staff_role) {
        this.staff_role = staff_role;
    }
}
