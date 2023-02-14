package com.api.vetgroup.dtos;

import com.api.vetgroup.models.Role;
import com.api.vetgroup.models.enums.StaffRole;

public class StaffReducedDto {

    private Long id;
    private String full_name;
    private Role role;
    private String avatar_url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }
}
