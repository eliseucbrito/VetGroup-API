package com.api.vetgroup.services;

import com.api.vetgroup.models.Patient;
import com.api.vetgroup.models.StaffUser;
import com.api.vetgroup.repositories.PatientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository repository;

    public List<Patient> findAll() {return repository.findAll();}

    public Patient findById(Long id) {
        Optional<Patient> obj = repository.findById(id);
        return obj.get();
    }

    @Transactional
    public Patient insert(Patient newPatient) {
        return repository.save(newPatient);
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public void update(Patient patient) {
        repository.save(patient);
    }
}
