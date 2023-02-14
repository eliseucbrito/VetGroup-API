package com.api.vetgroup.services.customMappers;

import com.api.vetgroup.models.enums.StaffRole;
import org.springframework.stereotype.Service;

@Service
public class RoleMapper {

    public String convertCodeToString(Long code) {
        String role = String.valueOf(StaffRole.valueOf(code.intValue()));
        return role;
    }
}
