package com.api.vetgroup.dtos.response;

import com.api.vetgroup.models.VetService;
import com.api.vetgroup.models.enums.PatientKind;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.List;

public class PatientResponseDto {

    private Long id;
    private String owner;
    private String name;
    private String owner_contact;
    private String breed;
    private Date birth_date;
    private String avatar_url;
    private PatientKind kind;
    private List<VetService> services;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public PatientKind getKind() {
        return kind;
    }

    public void setKind(PatientKind kind) {
        this.kind = kind;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner_contact() {
        return owner_contact;
    }

    public void setOwner_contact(String owner_contact) {
        this.owner_contact = owner_contact;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public List<VetService> getService() {
        return services;
    }

    public void setService(List<VetService> services) {
        this.services = services;
    }
}
