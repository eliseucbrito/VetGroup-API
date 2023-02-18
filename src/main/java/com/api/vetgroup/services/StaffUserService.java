package com.api.vetgroup.services;

import com.api.vetgroup.models.RoleHistoric;
import com.api.vetgroup.models.StaffUser;
import com.api.vetgroup.repositories.StaffRepository;
import com.api.vetgroup.security.jwt.JwtTokenProvider;
import com.api.vetgroup.services.customMappers.UserMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StaffUserService {

    @Autowired
    private StaffRepository repository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    public List<StaffUser> findAll(String sort_by, String direction) {
        var dir = Objects.equals(direction.toUpperCase(), "ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;
        return  repository.findAll(Sort.by(dir, sort_by));
    }

    public StaffUser findById(Long id) {
        Optional<StaffUser> obj = repository.findById(id);

        return obj.get();
    }

    public void setOnDutyState(Long id, Boolean on_duty) {
        StaffUser staff = findById(id);
        staff.setOnDuty(on_duty);
        update(staff);
    }

    public void setNewRole(RoleHistoric new_role) {
        StaffUser staff = new_role.getStaff();

        if (staff.getRole() == new_role.getRole()) {
            throw new IllegalArgumentException("This employee is already in the role of "+ new_role.getRole());
        }

        staff.setRole(new_role.getRole());
        staff.setWeeklyWorkLoad(new_role.getWeeklyWorkLoad());
        staff.setBaseSalary(new_role.getBaseSalary());

        update(staff);
    }

    @Transactional
    public StaffUser insert(StaffUser newUser) {
        StaffUser staffByCpf = repository.findByCpf(newUser.getCpf());
        StaffUser staffByEmail = repository.findByEmail(newUser.getEmail());

        if (staffByCpf != null) {
            throw new IllegalArgumentException("This CPF is already registered, try logging");
        }

        if (staffByEmail != null) {
            throw new IllegalArgumentException("This email is already registered, try logging");
        }

        return repository.save(newUser);
    }

    public StaffUser findByJwt(String jwtToken) {

        String jwtFormatted = jwtToken.substring("Bearer ".length());
        String[] chunks = jwtToken.split("\\.");

        String userEmail = jwtTokenProvider.decodedToken(jwtFormatted).getSubject();

        StaffUser staff = repository.findByEmail(userEmail);
        return staff;
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