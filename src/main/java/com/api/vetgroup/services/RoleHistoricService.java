package com.api.vetgroup.services;


import com.api.vetgroup.models.StaffRoleHistoric;
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

    public List<StaffRoleHistoric> findAll() {
        return repository.findAll();
    }

    public StaffRoleHistoric findById(Long id) {
        Optional<StaffRoleHistoric> obj =  repository.findById(id);
        return obj.get();
    }

    @Transactional
    public StaffRoleHistoric insert(StaffRoleHistoric new_role) {
        return repository.save(new_role);
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public void update(StaffRoleHistoric user) {
        repository.save(user);
    }
}
