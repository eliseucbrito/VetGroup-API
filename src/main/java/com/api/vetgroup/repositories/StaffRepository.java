package com.api.vetgroup.repositories;

import com.api.vetgroup.models.StaffUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<StaffUser, Long> {

    StaffUser findByEmail(String email);
    StaffUser findByCpf(String cpf);
}
