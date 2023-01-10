package com.api.vetgroup.dtos;

import com.api.vetgroup.models.enums.ServiceCity;
import com.api.vetgroup.models.enums.ServiceStatus;
import com.api.vetgroup.models.enums.ServiceTypes;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class VetServiceDto {

    @NotBlank
    @Size(min = 20)
    private String description;
    private Long staff_id;
    private Long patient_id;
    private Double price;
    private ServiceTypes type;
    private ServiceStatus status;
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
