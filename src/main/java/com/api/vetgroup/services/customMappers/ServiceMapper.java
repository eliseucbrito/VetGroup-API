package com.api.vetgroup.services.customMappers;

import com.api.vetgroup.dtos.create.ServiceCreateDto;
import com.api.vetgroup.dtos.response.ServiceResponseDto;
import com.api.vetgroup.dtos.StaffReducedDto;
import com.api.vetgroup.dtos.response.PatientResponseDto;
import com.api.vetgroup.models.Patient;
import com.api.vetgroup.models.StaffUser;
import com.api.vetgroup.models.VetService;
import com.api.vetgroup.models.enums.ServiceStatus;
import com.api.vetgroup.models.enums.ServiceTypes;
import com.api.vetgroup.services.PatientService;
import com.api.vetgroup.services.StaffUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ServiceMapper {

    @Autowired
    private StaffMapper staffMapper;

    @Autowired
    private PatientMapper patientMapper;

    @Autowired
    private StaffUserService staffService;

    @Autowired
    private PatientService patientService;

    public ServiceResponseDto convertServiceToDto(VetService service) {
        try {
            ServiceResponseDto serviceDto = new ServiceResponseDto();
            BeanUtils.copyProperties(service, serviceDto);

            StaffReducedDto staff = staffMapper.convertStaffToReducedDto(service.getStaff());
            PatientResponseDto patient = patientMapper.convertPatientToDto(service.getPatient());

            serviceDto.setStaff(staff);
            serviceDto.setPatient(patient);

            return serviceDto;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public VetService convertDtoToService(ServiceCreateDto serviceDto, String authorization) {
        try {
            VetService service = new VetService();
            StaffUser staffCreator = staffService.findByJwt(authorization);

            if (!staffCreator.getOnDuty())  {
                throw new IllegalArgumentException("You aren't on duty");
            }

            BeanUtils.copyProperties(serviceDto, service);

            StaffUser staff = staffService.findById(serviceDto.getStaff_id());
            Patient patient = patientService.findById(serviceDto.getPatient_id());

            if (serviceDto.getServiceDate() != null) {
                service.setServiceDate(new Date(serviceDto.getServiceDate()));
            } else {
                service.setServiceDate(new Date());
            }
            service.setType(serviceDto.getType());
            service.setStaff(staff);
            service.setPatient(patient);
            service.setCity(serviceDto.getCity());
            service.setStatus(serviceDto.getStatus());

            if (serviceDto.getType() == ServiceTypes.EMERGENCY) {
                service.setStatus(ServiceStatus.WAITING_PAYMENT);
            }

            return service;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<ServiceResponseDto> convertListToDto(List<VetService> list) {
        List<ServiceResponseDto> listDto = new ArrayList<ServiceResponseDto>();

        for(VetService service : list) {
            listDto.add(convertServiceToDto(service));
        }
        return listDto;
    }

}
