package com.api.vetgroup.models;

import com.api.vetgroup.models.enums.PatientKind;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "TB_PATIENTS")
public class Patient implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = false)
    private Integer kind;
    @Column(nullable = false, unique = false)
    private String owner;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "avatar_url", nullable = false, unique = false)
    private String avatarUrl;
    @Column(name = "owner_contact", nullable = false, unique = false)
    private String ownerContact;
    @Column(name = "birth_date", nullable = false, unique = false)
    private Date birthDate;
    @Column(nullable = false, unique = false)
    private String name;
    @Column(nullable = false, unique = false)
    private String breed;

    @JsonIgnore
    @OneToMany(mappedBy = "patient")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<VetService> service = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PatientKind getKind() {
        return PatientKind.valueOf(kind);
    }

    public void setKind(PatientKind kind) {
        if (kind != null) {
            this.kind = kind.getCode();
        }
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getOwnerContact() {
        return ownerContact;
    }

    public void setOwnerContact(String ownerContact) {
        this.ownerContact = ownerContact;
    }

    public Date getBirthDate() {
        return birthDate;
    }


    public void setBirthDate(String birthDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        try {
            Date dateFormatted = format.parse(birthDate);
            this.birthDate = dateFormatted;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public List<VetService> getService() {
        return service;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Patient patient = (Patient) o;

        return Objects.equals(id, patient.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
