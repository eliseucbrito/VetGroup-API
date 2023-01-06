package com.api.vetgroup.dtos;

import com.api.vetgroup.models.enums.PatientKind;
import com.api.vetgroup.models.enums.PatientSity;
import com.api.vetgroup.models.enums.PatientStatus;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Date;

public class PatientDto {

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
    private String birth_date;
    private String avatar_url;
    private PatientSity sity;
    private PatientStatus status;
    private PatientKind kind;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public PatientSity getSity() {
        return sity;
    }

    public void setSity(PatientSity sity) {
        this.sity = sity;
    }

    public PatientStatus getStatus() {
        return status;
    }

    public void setStatus(PatientStatus status) {
        this.status = status;
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
