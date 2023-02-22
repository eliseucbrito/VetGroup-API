package com.api.vetgroup.services.customMappers;

import com.api.vetgroup.dtos.create.PatientCreateDto;
import com.api.vetgroup.dtos.response.PatientResponseDto;
import com.api.vetgroup.models.Patient;
import com.api.vetgroup.models.StaffUser;
import com.api.vetgroup.services.StaffUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientMapper {

    @Autowired
    private StaffUserService staffService;

    public PatientResponseDto convertPatientToDto(Patient patient) {
        try {
            PatientResponseDto patientDto = new PatientResponseDto();
            BeanUtils.copyProperties(patient, patientDto);

            patientDto.setKind(patient.getKind());
            patientDto.setBirthDate(patient.getBirthDate());

            return patientDto;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Patient convertDtoToPatient(PatientCreateDto patientDto , String authorization) {
        try {
            Patient patient = new Patient();
            StaffUser staff = staffService.findByJwt(authorization);

            if (!staff.getOnDuty())  {
                throw new IllegalArgumentException("You aren't on duty");
            }

            BeanUtils.copyProperties(patientDto, patient);

            patient.setKind(patientDto.getKind());
            patient.setBirthDate(patientDto.getBirthDate());
            return patient;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<PatientResponseDto> convertListToDto(List<Patient> list) {
        try {
            List<PatientResponseDto> listDto = new ArrayList<PatientResponseDto>();

            for(Patient patient : list) {
                listDto.add(convertPatientToDto(patient));
            }
            return listDto;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
