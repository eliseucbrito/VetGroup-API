package com.api.vetgroup.dtos.create;

import com.api.vetgroup.models.enums.ReportTypes;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;


public class ReportCreateDto {

    private LocalDateTime created_at;
    @NotBlank
    @Size(max = 70, min = 15)
    private String title;
    @NotBlank
    private String description;
    private Integer payment_value;
    private Long staff_id;
    private ReportTypes type;

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

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

    public Integer getPayment_value() {
        return payment_value;
    }

    public void setPayment_value(Integer payment_value) {
        this.payment_value = payment_value;
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
}
