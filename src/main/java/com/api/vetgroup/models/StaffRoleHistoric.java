package com.api.vetgroup.models;

import com.api.vetgroup.models.StaffUser;
import com.api.vetgroup.models.enums.StaffRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "TB_ROLE_HISTORIC")
public class StaffRoleHistoric implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = false)
    private LocalDateTime started_in;
    @Column(nullable = false, unique = false)
    private Integer role;
    @Column(nullable = false, unique = false)
    private Integer base_salary;
    private Integer weekly_work_load;

    @ManyToOne
    @JoinColumn(name = "promoted_by")
    private StaffUser promoted_by;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private StaffUser staff;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStarted_in() {
        return started_in;
    }

    public void setStarted_in(LocalDateTime started_in) {
        this.started_in = started_in;
    }

    public StaffRole getRole() {
        return StaffRole.valueOf(role);
    }

    public void setRole(StaffRole role) {
        if (role != null) {
            this.role = role.getCode();
        }
    }

    public Integer getBase_salary() {
        return base_salary;
    }

    public void setBase_salary(Integer base_salary) {
        this.base_salary = base_salary;
    }

    public StaffUser getPromoted_by() {
        return promoted_by;
    }

    public void setPromoted_by(StaffUser promoted_by) {
        this.promoted_by = promoted_by;
    }

    public Integer getWeekly_work_load() {
        return weekly_work_load;
    }

    public void setWeekly_work_load(Integer weekly_work_load) {
        this.weekly_work_load = weekly_work_load;
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

        StaffRoleHistoric that = (StaffRoleHistoric) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
