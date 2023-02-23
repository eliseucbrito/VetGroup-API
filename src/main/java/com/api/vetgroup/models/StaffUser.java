package com.api.vetgroup.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

import com.api.vetgroup.models.enums.StaffRole;


@Entity
@Table(name = "TB_STAFF")
public class StaffUser implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = false)
    private Long role;
    @Column(name = "full_name", nullable = false, unique = false, length = 70)
    private String fullName;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(name = "avatar_url", nullable = true, unique = false)
    private String avatarUrl;

    @Column(name = "created_at", nullable = false, unique = false)
    private LocalDateTime createdAt;
    @Column(nullable = false, unique = true, length = 11)
    private String cpf;
    @Column(name = "base_salary", nullable = true, unique = false)
    private Integer baseSalary;
    @Column(name = "on_duty", nullable = true, unique = false)
    private Boolean onDuty;

    @Column(name = "weekly_work_load", nullable = true, unique = false)
    private Integer weeklyWorkLoad; // in minutes

    @Column(name = "work_load_completed", nullable = true, unique = false)
    private Integer workLoadCompleted; // in minutes

    @JsonIgnore
    @OneToMany(mappedBy = "staff")
    private List<Report> reports = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "approvedBy")
    private List<Report> reports_approved = new ArrayList<>();

    @OneToMany(mappedBy = "staff")
    private List<RoleHistoric> role_historic = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "promoter")
    private List<RoleHistoric> promoter_historic = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "staff")
    private List<VetService> service = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "staff")
    private List<Room> room = new ArrayList<>();

    @OneToMany(mappedBy = "staff")
    private List<RoomAccessList> access_list = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRole() {
        return role;
    }

    public void setRole(Long role) {
        this.role = role;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Integer getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(Integer baseSalary) {
        this.baseSalary = baseSalary;
    }

    public Boolean getOnDuty() {
        return onDuty;
    }

    public void setOnDuty(Boolean onDuty) {
        if (onDuty == null) {
            this.onDuty = false;
        } else {
            this.onDuty = onDuty;
        }
    }

    public Integer getWeeklyWorkLoad() {
        return weeklyWorkLoad;
    }

    public void setWeeklyWorkLoad(Integer weeklyWorkLoad) {
        this.weeklyWorkLoad = weeklyWorkLoad;
    }

    public Integer getWorkLoadCompleted() {
        return workLoadCompleted;
    }

    public void setWorkLoadCompleted(Integer workLoadCompleted) {
        this.workLoadCompleted = workLoadCompleted;
    }

    public List<Report> getReports_approved() {
        return reports_approved;
    }

    public List<RoleHistoric> getRole_historic() {
        return role_historic;
    }

    public List<RoleHistoric> getPromoter_historic() {
        return promoter_historic;
    }

    public List<Report> getReports() {
        return reports;
    }

    public List<VetService> getService() {
        return service;
    }

    public List<Room> getRoom() {
        return room;
    }

    public List<RoomAccessList> getAccess_lists() {
        return access_list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StaffUser staffUser = (StaffUser) o;

        return Objects.equals(id, staffUser.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
