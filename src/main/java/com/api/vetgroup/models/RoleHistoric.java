package com.api.vetgroup.models;

import com.api.vetgroup.models.enums.StaffRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "TB_ROLE_HISTORIC")
public class RoleHistoric implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "started_in", nullable = false, unique = false)
    private LocalDateTime startedIn;

    @Column(nullable = false, unique = false)
    private Long role;
    @Column(name = "base_salary", nullable = false, unique = false)
    private Integer baseSalary;

    @Column(name = "weekly_work_load")
    private Integer weeklyWorkLoad;

    @ManyToOne
    @JoinColumn(name = "promoted_by")
    private StaffUser promoter;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private StaffUser staff;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartedIn() {
        return startedIn;
    }

    public void setStartedIn(LocalDateTime startedIn) {
        this.startedIn = startedIn;
    }

    public Long getRole() {
        return role;
    }

    public void setRole(Long role) {
        this.role = role;
    }

    public Integer getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(Integer baseSalary) {
        this.baseSalary = baseSalary;
    }

    public Integer getWeeklyWorkLoad() {
        return weeklyWorkLoad;
    }

    public void setWeeklyWorkLoad(Integer weeklyWorkLoad) {
        this.weeklyWorkLoad = weeklyWorkLoad;
    }

    public StaffUser getPromoter() {
        return promoter;
    }

    public void setPromoter(StaffUser promoter) {
        this.promoter = promoter;
    }

    @JsonIgnore
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

        RoleHistoric that = (RoleHistoric) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
