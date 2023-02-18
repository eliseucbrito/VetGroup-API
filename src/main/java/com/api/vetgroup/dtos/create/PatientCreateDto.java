package com.api.vetgroup.dtos.create;

import com.api.vetgroup.models.enums.PatientKind;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class PatientCreateDto {

    private LocalDateTime createdAt;
    @NotBlank
    @Size(max = 15)
    private String owner;
    @NotBlank
    @Size(max = 15)
    private String name;
    @NotBlank
    @Size(max = 11)
    private String ownerContact;
    @NotBlank
    private String breed;
    @NotBlank
    private String birthDate;
    private String avatarUrl;
    @NotNull
    private PatientKind kind;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnerContact() {
        return ownerContact;
    }

    public void setOwnerContact(String ownerContact) {
        this.ownerContact = ownerContact;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public PatientKind getKind() {
        return kind;
    }

    public void setKind(PatientKind kind) {
        this.kind = kind;
    }
}
