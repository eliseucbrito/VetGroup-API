package com.api.vetgroup.models;

import com.api.vetgroup.models.enums.PaymentStatus;
import com.api.vetgroup.models.enums.ServiceCity;
import com.api.vetgroup.models.enums.ServiceStatus;
import com.api.vetgroup.models.enums.ServiceTypes;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "TB_SERVICE")
public class VetService implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = false)
    private String title;
    @Column(nullable = false, unique = false)
    private String description;
    @Column(name = "created_at", nullable = false, unique = false)
    private LocalDateTime createdAt;
    @Column(name = "service_date", nullable = true, unique = false)
    private Date serviceDate;
    @Column(nullable = false, unique = false)
    private Integer type;
    @Column(name = "payment_status", nullable = false, unique = false)
    private Integer paymentStatus;
    @Column(nullable = false, unique = false)
    private Integer status;
    @Column(nullable = false, unique = false)
    private Integer city;
    @Column(nullable = true, unique = false)
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private StaffUser staff;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Date getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(Date serviceDate) {
        this.serviceDate = serviceDate;
    }

    public ServiceTypes getType() {
        return ServiceTypes.valueOf(type);
    }

    public void setType(ServiceTypes type) {
        if (type != null) {
            this.type = type.getCode();
        }
    }

    public PaymentStatus getPaymentStatus() {
        return PaymentStatus.valueOf(paymentStatus);
    }

    public void setPaymentStatus(PaymentStatus status) {
        if (status != null) {
            this.paymentStatus = status.getCode();
        }
    }

    public ServiceStatus getStatus() {
        return ServiceStatus.valueOf(status);
    }

    public void setStatus(ServiceStatus status) {
        if (status != null) {
            this.status = status.getCode();
        }
    }

    public ServiceCity getCity() {
        return ServiceCity.valueOf(city);
    }

    public void setCity(ServiceCity city) {
        if (city != null) {
            this.city = city.getCode();
        }
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public StaffUser getStaff() {
        return staff;
    }

    public void setStaff(StaffUser staff) {
        this.staff = staff;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VetService that = (VetService) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
