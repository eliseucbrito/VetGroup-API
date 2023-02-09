package com.api.vetgroup.services;

import com.api.vetgroup.models.RoleHistoric;
import com.api.vetgroup.models.StaffUser;
import com.api.vetgroup.repositories.StaffRepository;
import com.api.vetgroup.security.jwt.JwtTokenProvider;
import com.api.vetgroup.services.customMappers.UserMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StaffUserService {

    @Autowired
    private StaffRepository repository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    public List<StaffUser> findAll() {
        return repository.findAll();
    }

    public StaffUser findById(Long id) {
        Optional<StaffUser> obj = repository.findById(id);

        return obj.get();
    }

    public void setOnDutyState(Long id, Boolean on_duty) {
        StaffUser staff = findById(id);
        staff.setOn_duty(on_duty);
        update(staff);
    }

    public void setNewRole(RoleHistoric new_role) {
        StaffUser staff = new_role.getStaff();

        if (staff.getRole() == new_role.getRole()) {
            throw new IllegalArgumentException("This employee is already in the role of "+ new_role.getRole());
        }

        staff.setRole(new_role.getRole());
        staff.setWeekly_work_load(new_role.getWeekly_work_load());
        staff.setBase_salary(new_role.getBase_salary());

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