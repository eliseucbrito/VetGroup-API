package com.api.vetgroup.services.customMappers;

import com.api.vetgroup.dtos.RoomAccessResponseDto;
import com.api.vetgroup.models.RoomAccessList;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomAccessMapper {

    @Autowired
    private StaffMapper staffMapper;

    public List<RoomAccessResponseDto> convertModelToDto(List<RoomAccessList> list) {
        List<RoomAccessResponseDto> listDto = new ArrayList<RoomAccessResponseDto>();
        RoomAccessResponseDto accessDto = new RoomAccessResponseDto();

        for(RoomAccessList access : list) {
            BeanUtils.copyProperties(access, accessDto);
            accessDto.setStaff(staffMapper.convertStaffToReducedDto(access.getStaff()));
            listDto.add(accessDto);
        }
        return listDto;
    }
}
