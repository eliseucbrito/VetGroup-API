package com.api.vetgroup.services.customMappers;

import com.api.vetgroup.dtos.create.PatientCreateDto;
import com.api.vetgroup.dtos.response.PatientResponseDto;
import com.api.vetgroup.models.Patient;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientMapper {

    public PatientResponseDto convertPatientToDto(Patient patient) {
        try {
            PatientResponseDto patientDto = new PatientResponseDto();
            BeanUtils.copyProperties(patient, patientDto);

            patientDto.setKind(patient.getKind());
            patientDto.setBirthDate(patient.getBirthDate());

            return patientDto;
        } catch (Exception e) {
            throw new RuntimeException("Error during conversion to PatientDto");
        }
    }

    public Patient convertDtoToPatient(PatientCreateDto patientDto) {
        try {
            Patient patient = new Patient();
            BeanUtils.copyProperties(patientDto, patient);

            patient.setKind(patientDto.getKind());
            patient.setBirthDate(patientDto.getBirthDate());
            return patient;
        } catch (Exception e) {
            throw new RuntimeException("Error during conversion to Patient");
        }
    }

    public List<PatientResponseDto> convertListToDto(List<Patient> list) {
        List<PatientResponseDto> listDto = new ArrayList<PatientResponseDto>();

        for(Patient patient : list) {
            listDto.add(convertPatientToDto(patient));
        }
        return listDto;
    }
}
