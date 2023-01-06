package com.api.vetgroup.services;

import com.api.vetgroup.models.StaffUser;
import com.api.vetgroup.repositories.StaffRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StaffUserService {

    @Autowired
    private StaffRepository repository;

    public List<StaffUser> findAll() {
        return repository.findAll();
    }

    public StaffUser findById(Long id) {
        Optional<StaffUser> obj =  repository.findById(id);
        return obj.get();
    }

    @Transactional
    public StaffUser insert(StaffUser newUser) {
        return repository.save(newUser);
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public void update(StaffUser user) {
        repository.save(user);
    }
}
