package com.api.vetgroup.repositories;

import com.api.vetgroup.models.VetService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<VetService, Long> {

    @Query(value = "SELECT * FROM tb_service as S WHERE S.patient_id = ?1 ORDER BY S.created_at DESC", nativeQuery = true)
    public List<VetService> findServicesByPatientId(Long id);

    @Query(value = "SELECT *\n" +
            "FROM tb_service\n" +
            "WHERE tb_service.staff_id = ?1 ORDER BY tb_service.created_at DESC", nativeQuery = true)
    public List<VetService> findServiceByStaffId(Long id);
}
 