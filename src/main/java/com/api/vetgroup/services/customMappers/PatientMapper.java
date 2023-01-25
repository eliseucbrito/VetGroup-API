package com.api.vetgroup.services.customMappers;

import com.api.vetgroup.dtos.create.PatientCreateDto;
import com.api.vetgroup.dtos.response.PatientResponseDto;
import com.api.vetgroup.models.Patient;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class PatientMapper {

    public PatientResponseDto convertPatientToDto(Patient patient) {
        try {
            PatientResponseDto patientDto = new PatientResponseDto();
            BeanUtils.copyProperties(patient, patientDto);

            patientDto.setKind(patient.getKind());
            patientDto.setService(patient.getService());
            patientDto.setBirth_date(patient.getBirth_date());

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
            patient.setBirth_date(patientDto.getBirth_date());
            return patient;
        } catch (Exception e) {
            throw new RuntimeException("Error during conversion to Patient");
        }
    }
}
