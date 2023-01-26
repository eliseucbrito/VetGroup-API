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
    private Integer staff_role;
    @Column(nullable = false, unique = false, length = 70)
    private String full_name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = false)
    private String password;
    @Column(nullable = true, unique = false)
    private String avatar_url;
    private LocalDateTime created_at;
    @Column(nullable = false, unique = true, length = 11)
    private String cpf;
    @Column(nullable = true, unique = false)
    private Integer base_salary;
    @Column(nullable = true, unique = false)
    private Boolean on_duty;

    @Column(nullable = true, unique = false)
    private Integer weekly_work_load; // in minutes

    @Column(nullable = true, unique = false)
    private Integer work_load_completed; // in minutes

    @JsonIgnore
    @OneToMany(mappedBy = "staff")
    private List<Report> reports = new ArrayList<>();

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

    public StaffRole getStaff_Role() {
        return StaffRole.valueOf(staff_role);
    }

    public void setStaff_Role(StaffRole staff_role) {
        if (staff_role != null) {
            this.staff_role = staff_role.getCode();
        }
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Integer getBase_salary() {
        return base_salary;
    }

    public void setBase_salary(Integer base_salary) {
        this.base_salary = base_salary;
    }

    public Boolean getOn_duty() {
        return on_duty;
    }

    public void setOn_duty(Boolean on_duty) {
        if (on_duty == null) {
            this.on_duty = false;
        } else {
            this.on_duty = on_duty;
        }
    }

    public Integer getWeekly_work_load() {
        return weekly_work_load;
    }

    public void setWeekly_work_load(Integer weekly_work_load) {
        this.weekly_work_load = weekly_work_load;
    }

    public Integer getWork_load_completed() {
        return work_load_completed;
    }

    public void setWork_load_completed(Integer work_load_completed) {
        this.work_load_completed = work_load_completed;
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
