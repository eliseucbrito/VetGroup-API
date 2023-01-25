package com.api.vetgroup.dtos.create;

import com.api.vetgroup.models.enums.PatientKind;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class PatientCreateDto {

    private LocalDateTime created_at;
    @NotBlank
    @Size(max = 15)
    private String owner;
    @NotBlank
    @Size(max = 15)
    private String name;
    @NotBlank
    @Size(max = 11)
    private String owner_contact;
    @NotBlank
    private String breed;
    @NotBlank
    private String birth_date;
    private String avatar_url;
    @NotNull
    private PatientKind kind;

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
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

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }
}
