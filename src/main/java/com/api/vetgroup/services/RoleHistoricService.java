package com.api.vetgroup.services;


import com.api.vetgroup.models.RoleHistoric;
import com.api.vetgroup.repositories.RoleHistoricRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleHistoricService {

    @Autowired
    private RoleHistoricRepository repository;

    public List<RoleHistoric> findAll() {
        return repository.findAll();
    }

    public RoleHistoric findById(Long id) {
        Optional<RoleHistoric> obj =  repository.findById(id);
        return obj.get();
    }

    @Transactional
    public RoleHistoric insert(RoleHistoric new_role) {
        return repository.save(new_role);
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public void update(RoleHistoric user) {
        repository.save(user);
    }
}
