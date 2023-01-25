package com.api.vetgroup.dtos;

import com.api.vetgroup.models.StaffUser;
import com.api.vetgroup.models.enums.ReportTypes;

import java.time.LocalDateTime;

public class ReportResponseDto {

    private Long id;
    private String title;
    private String description;
    private Integer payment_value;
    private ReportTypes type;
    private LocalDateTime created_at;
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

    public Integer getPayment_value() {
        return payment_value;
    }

    public void setPayment_value(Integer payment_value) {
        this.payment_value = payment_value;
    }

    public ReportTypes getType() {
        return type;
    }

    public void setType(ReportTypes type) {
        this.type = type;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
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
