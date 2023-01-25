package com.api.vetgroup.services.customMappers;

import com.api.vetgroup.dtos.PromoterDto;
import com.api.vetgroup.dtos.RoleHistoricResponseDto;
import com.api.vetgroup.dtos.StaffCreateDto;
import com.api.vetgroup.dtos.StaffResponseDto;
import com.api.vetgroup.models.RoleHistoric;
import com.api.vetgroup.models.StaffUser;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
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
                PromoterDto promoterDto = new PromoterDto();

                BeanUtils.copyProperties(historic, historicDtoV2);

                promoterDto.setId(historic.getPromoter().getId());
                promoterDto.setFull_name(historic.getPromoter().getFull_name());
                historicDtoV2.setPromoter(promoterDto);

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
