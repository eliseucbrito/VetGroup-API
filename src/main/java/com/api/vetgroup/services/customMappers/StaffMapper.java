package com.api.vetgroup.services.customMappers;

import com.api.vetgroup.dtos.StaffReducedDto;
import com.api.vetgroup.dtos.response.RoleHistoricResponseDto;
import com.api.vetgroup.dtos.create.StaffCreateDto;
import com.api.vetgroup.dtos.response.StaffResponseDto;
import com.api.vetgroup.models.RoleHistoric;
import com.api.vetgroup.models.StaffUser;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StaffMapper {

    public StaffResponseDto convertStaffToDto(StaffUser staff) {
        try {
            StaffResponseDto staffDto = new StaffResponseDto();
            BeanUtils.copyProperties(staff, staffDto);

            List<RoleHistoricResponseDto> roleHistoricResponseDto = new ArrayList<>();
            staffDto.setStaff_role(staff.getStaffRole());

            for(RoleHistoric historic : staff.getRole_historic()) {
                RoleHistoricResponseDto historicDtoV2 = new RoleHistoricResponseDto();
                StaffReducedDto staffReducedDto = new StaffReducedDto();

                BeanUtils.copyProperties(historic, historicDtoV2);

                staffReducedDto.setId(historic.getPromoter().getId());
                staffReducedDto.setFull_name(historic.getPromoter().getFull_name());
                staffReducedDto.setRole(historic.getRole());

                historicDtoV2.setPromoter(staffReducedDto);

                roleHistoricResponseDto.add(historicDtoV2);
            }

            staffDto.setRole_historic(roleHistoricResponseDto);
            return staffDto;
        } catch (Exception e) {
            throw new RuntimeException("Error during conversion to StaffDto");
        }
    }

    public StaffUser convertDtoToStaff(StaffCreateDto staffDto) {
        try {
            var staffUserModel = new StaffUser();
            BeanUtils.copyProperties(staffDto, staffUserModel);

            staffUserModel.setStaffRole(staffDto.getStaff_role());
            return staffUserModel;
        } catch (Exception e) {
            throw new RuntimeException("Error during conversion to StaffUser");
        }
    }
}
