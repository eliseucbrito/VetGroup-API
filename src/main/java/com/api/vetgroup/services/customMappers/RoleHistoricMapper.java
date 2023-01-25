package com.api.vetgroup.services.customMappers;

import com.api.vetgroup.dtos.create.RoleHistoricCreateDto;
import com.api.vetgroup.models.RoleHistoric;
import com.api.vetgroup.models.StaffUser;
import com.api.vetgroup.services.StaffUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleHistoricMapper {

    @Autowired
    private StaffUserService staffService;

    public RoleHistoric convertToRoleHistoric(RoleHistoricCreateDto new_role, Long staff_id) {
        try {
            RoleHistoric roleHistoric = new RoleHistoric();
            BeanUtils.copyProperties(new_role, roleHistoric);

            StaffUser promoter = staffService.findById(new_role.getPromoted_by());
            StaffUser staff = staffService.findById(staff_id);

            roleHistoric.setRole(new_role.getRole());
            roleHistoric.setPromoter(promoter);
            roleHistoric.setStaff(staff);

            return roleHistoric;
        } catch (Exception e) {
            throw new RuntimeException("Error during conversion to RoleHistoric");
        }
    }
}
