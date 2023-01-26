package com.api.vetgroup.models;

import com.api.vetgroup.models.enums.ServiceCity;
import com.api.vetgroup.models.enums.ServiceStatus;
import com.api.vetgroup.models.enums.ServiceTypes;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "TB_SERVICE")
public class VetService implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = false)
    private String description;
    @Column(nullable = false, unique = false)
    private LocalDateTime created_at;
    @Column(nullable = false, unique = false)
    private Integer type;
    @Column(nullable = false, unique = false)
    private Integer status;
    @Column(nullable = false, unique = false)
    private Integer city;
    @Column(nullable = true, unique = false)
    private Double price;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public ServiceTypes getType() {
        return ServiceTypes.valueOf(type);
    }

    public void setType(ServiceTypes type) {
        if (type != null) {
            this.type = type.getCode();
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
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
