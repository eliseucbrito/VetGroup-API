package com.api.vetgroup.repositories;

import com.api.vetgroup.models.RoleHistoric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleHistoricRepository extends JpaRepository<RoleHistoric, Long> {
}
