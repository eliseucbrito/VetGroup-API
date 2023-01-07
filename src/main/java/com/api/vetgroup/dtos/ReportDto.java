package com.api.vetgroup.dtos;

import com.api.vetgroup.models.enums.ReportTypes;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;


public class ReportDto {


    @NotBlank
    @Size(max = 70, min = 15)
    private String title;
    @NotBlank
    private String description;
    @NotNull
    private Long staff_id;
    private ReportTypes type;
    private Boolean approved;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(Long staff_id) {
        this.staff_id = staff_id;
    }

    public ReportTypes getType() {
        return type;
    }

    public void setType(ReportTypes type) {
        this.type = type;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }
}
