package com.api.vetgroup.services;

import com.api.vetgroup.models.Patient;
import com.api.vetgroup.repositories.PatientRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository repository;

    public List<Patient> findAll(String sort_by, String direction) {
        var dir = Objects.equals(direction, "ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;
        List<Patient> list = new ArrayList<>();

        if (!Objects.equals(sort_by, "created_at") && !Objects.equals(sort_by, "birth_date")) {
            if (Objects.equals(direction, "ASC")) {
                list = repository.findAll(Sort.by(Sort.Direction.ASC, sort_by));
            } else {
                list = repository.findAll(Sort.by(Sort.Direction.DESC, sort_by));
            }
        }


        return list;
    }

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
