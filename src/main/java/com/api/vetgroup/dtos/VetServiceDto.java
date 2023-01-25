package com.api.vetgroup.dtos;

import com.api.vetgroup.models.enums.ServiceCity;
import com.api.vetgroup.models.enums.ServiceStatus;
import com.api.vetgroup.models.enums.ServiceTypes;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class VetServiceDto {

    private LocalDateTime created_at;
    @NotBlank
    @Size(min = 20)
    private String description;
    @NotNull
    private Long staff_id;

    @NotNull
    private Long patient_id;

    @NotNull
    private Double price;

    @NotNull
    private ServiceTypes type;

    @NotNull
    private ServiceStatus status;

    @NotNull
    private ServiceCity city;


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

    public Long getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(Long patient_id) {
        this.patient_id = patient_id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public ServiceTypes getType() {
        return type;
    }

    public void setType(ServiceTypes type) {
        this.type = type;
    }

    public ServiceStatus getStatus() {
        return status;
    }

    public void setStatus(ServiceStatus status) {
        this.status = status;
    }

    public ServiceCity getCity() {
        return city;
    }

    public void setCity(ServiceCity city) {
        this.city = city;
    }
}
