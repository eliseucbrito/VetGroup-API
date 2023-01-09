package com.api.vetgroup.models;

import com.api.vetgroup.models.enums.PatientKind;
import com.api.vetgroup.models.enums.PatientSity;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private LocalDateTime created_at;
    @Column(nullable = false, unique = false)
    private String avatar_url;
    @Column(nullable = false, unique = false)
    private String owner_contact;
    @Column(nullable = false, unique = false)
    private Date birth_date;
    @Column(nullable = false, unique = false)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "patient")
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

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getOwner_contact() {
        return owner_contact;
    }

    public void setOwner_contact(String owner_contact) {
        this.owner_contact = owner_contact;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        try {
            Date dateFormatted = format.parse(birth_date);
            this.birth_date = dateFormatted;
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
