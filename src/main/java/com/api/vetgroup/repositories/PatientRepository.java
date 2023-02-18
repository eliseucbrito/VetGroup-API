package com.api.vetgroup.repositories;

import com.api.vetgroup.models.Patient;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    @Query(value = "SELECT * FROM tb_patients p ORDER BY p.created_at ASC", nativeQuery = true)
    List<Patient> findAllByCreatedAtOrderAsc();

    @Query(value = "SELECT * FROM tb_patients p ORDER BY p.created_at DESC", nativeQuery = true)
    List<Patient> findAllByCreatedAtOrderDesc();

//    @Query(value = "SELECT * FROM tb_patients p ORDER BY p.created_at ASC", nativeQuery = true)
//    List<Patient> findAllByBirthDate(Sort sort); NAO É POSSIVEL FAZER QUERY DINAMICA COM NATIVE QUERY
    @Query(value = "SELECT * FROM tb_patients p ORDER BY p.created_at DESC", nativeQuery = true)
    List<Patient> findAllByBirthDateOrderDesc();

//    @Query("select p from Patient p order by p.created_at DESC")
//    List<Patient> procurando(Sort sort);

}
