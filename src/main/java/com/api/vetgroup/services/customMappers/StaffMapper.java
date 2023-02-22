package com.api.vetgroup.services.customMappers;

import com.api.vetgroup.dtos.StaffReducedDto;
import com.api.vetgroup.dtos.response.RoleHistoricResponseDto;
import com.api.vetgroup.dtos.create.StaffCreateDto;
import com.api.vetgroup.dtos.response.StaffResponseDto;
import com.api.vetgroup.models.Role;
import com.api.vetgroup.models.RoleHistoric;
import com.api.vetgroup.models.StaffUser;
import com.api.vetgroup.repositories.RoleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StaffMapper {

    @Autowired
    private RoleRepository roleRepository;

    public StaffResponseDto convertStaffToDto(StaffUser staff) {
        try {
            StaffResponseDto staffDto = new StaffResponseDto();
            BeanUtils.copyProperties(staff, staffDto);

            Optional<Role> role = roleRepository.findById(staff.getRole());

            staffDto.setRole(role.get());
            staffDto.setAccess_list(staff.getAccess_lists());

            List<RoleHistoricResponseDto> roleHistoricResponseDto = new ArrayList<>();

            for(RoleHistoric historic : staff.getRole_historic()) {
                RoleHistoricResponseDto historicDtoV2 = new RoleHistoricResponseDto();
                StaffReducedDto promoter = new StaffReducedDto();

                Optional<Role> promoterRole = roleRepository.findById(historic.getPromoter().getRole());
                Optional<Role> historicRole = roleRepository.findById(historic.getRole());

                BeanUtils.copyProperties(historic, historicDtoV2);
                BeanUtils.copyProperties(historic.getPromoter(), promoter);

                historicDtoV2.setRole(historicRole.get().getDescription());
                promoter.setRole(promoterRole.get());
                historicDtoV2.setPromoter(promoter);

                roleHistoricResponseDto.add(historicDtoV2);
            }
            staffDto.setRole_historic(roleHistoricResponseDto);
            return staffDto;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public StaffUser convertDtoToStaff(StaffCreateDto staffDto) {
        try {
            StaffUser staffModel = new StaffUser();
            BeanUtils.copyProperties(staffDto, staffModel);

            Role role = roleRepository.findByDescription(staffDto.getRole());

            staffModel.setRole(role.getId());
            return staffModel;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public StaffReducedDto convertStaffToReducedDto(StaffUser staff) {
        try {
            StaffReducedDto staffReducedDto = new StaffReducedDto();
            BeanUtils.copyProperties(staff, staffReducedDto);

            Optional<Role> role = roleRepository.findById(staff.getRole());

            staffReducedDto.setRole(role.get());
            return staffReducedDto;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
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
