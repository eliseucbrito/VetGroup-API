package com.api.vetgroup.services;

import com.api.vetgroup.models.StaffUser;
import com.api.vetgroup.models.StaffRoleHistoric;
import com.api.vetgroup.repositories.StaffRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
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
        Optional<StaffUser> obj = repository.findById(id);
        return obj.get();
    }

    public void setOnDutyState(Long id, Boolean on_duty) {
        StaffUser staff = findById(id);
        staff.setOn_duty(on_duty);
        update(staff);
    }

    public void setNewRole(StaffRoleHistoric newRoleHistoric, StaffUser staff) {
        if (staff.getStaffRole() == newRoleHistoric.getRole()) {
            throw new IllegalArgumentException("This employee is already in the role of "+ newRoleHistoric.getRole());
        }

        staff.setStaffRole(newRoleHistoric.getRole());
        staff.setWeekly_work_load(newRoleHistoric.getWeekly_work_load());
        staff.setBase_salary(newRoleHistoric.getBase_salary());

        update(staff);
    }

    public List<StaffRoleHistoric> getRoleHistoricList(Long id) {
        StaffUser staff = findById(id);
        List<StaffRoleHistoric> roleHistoric = staff.getRole_historic();
        return roleHistoric;
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