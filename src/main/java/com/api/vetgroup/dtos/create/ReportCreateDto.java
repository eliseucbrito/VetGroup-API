package com.api.vetgroup.dtos.create;

import com.api.vetgroup.models.enums.ReportTypes;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;


public class ReportCreateDto {

    private LocalDateTime createdAt;
    @NotBlank
    @Size(max = 70, min = 15)
    private String title;
    @NotBlank
    private String description;
    private Integer paymentValue;
    private Long staff_id;
    private ReportTypes type;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getPaymentValue() {
        return paymentValue;
    }

    public void setPaymentValue(Integer paymentValue) {
        this.paymentValue = paymentValue;
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
