package com.api.vetgroup.repositories;

import com.api.vetgroup.models.VetService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<VetService, Long> {
}
