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

            staffDto.setStaff_role(staff.getStaff_Role());
            staffDto.setAccess_list(staff.getAccess_lists());

            List<RoleHistoricResponseDto> roleHistoricResponseDto = new ArrayList<>();

            for(RoleHistoric historic : staff.getRole_historic()) {
                RoleHistoricResponseDto historicDtoV2 = new RoleHistoricResponseDto();
                StaffReducedDto promoter = new StaffReducedDto();

                BeanUtils.copyProperties(historic, historicDtoV2);
                BeanUtils.copyProperties(historic.getPromoter(), promoter);

                promoter.setRole(historic.getPromoter().getStaff_Role());
                historicDtoV2.setPromoter(promoter);

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
            StaffUser staffUserModel = new StaffUser();
            BeanUtils.copyProperties(staffDto, staffUserModel);

            staffUserModel.setStaff_Role(staffDto.getStaff_role());
            return staffUserModel;
        } catch (Exception e) {
            throw new RuntimeException("Error during conversion to StaffUser");
        }
    }

    public StaffReducedDto convertStaffToReducedDto(StaffUser staff) {
        try {
            StaffReducedDto staffReducedDto = new StaffReducedDto();
            BeanUtils.copyProperties(staff, staffReducedDto);

            staffReducedDto.setRole(staff.getStaff_Role());
            return staffReducedDto;
        } catch (Exception e) {
            throw new RuntimeException("Error during conversion to StaffReducedDto");
        }
    }

    public List<StaffResponseDto> convertListToDto(List<StaffUser> list) {
        List<StaffResponseDto> listDto = new ArrayList<StaffResponseDto>();

        for(StaffUser staff : list) {
            listDto.add(convertStaffToDto(staff));
        }
        return listDto;
    }
}
