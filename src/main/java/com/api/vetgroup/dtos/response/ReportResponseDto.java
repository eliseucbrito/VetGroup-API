package com.api.vetgroup.dtos.response;

import com.api.vetgroup.dtos.StaffReducedDto;
import com.api.vetgroup.models.StaffUser;
import com.api.vetgroup.models.enums.ReportTypes;

import java.time.LocalDateTime;

public class ReportResponseDto {

    private Long id;
    private String title;
    private String description;
    private Integer paymentValue;
    private ReportTypes type;
    private LocalDateTime createdAt;
    private Boolean approved;
    private StaffReducedDto staff;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public ReportTypes getType() {
        return type;
    }

    public void setType(ReportTypes type) {
        this.type = type;
    }

    public Integer getPaymentValue() {
        return paymentValue;
    }

    public void setPaymentValue(Integer paymentValue) {
        this.paymentValue = paymentValue;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public StaffReducedDto getStaff() {
        return staff;
    }

    public void setStaff(StaffReducedDto staff) {
        this.staff = staff;
    }
}
