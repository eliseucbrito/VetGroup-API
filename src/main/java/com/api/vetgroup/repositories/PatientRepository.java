package com.api.vetgroup.repositories;

import com.api.vetgroup.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {


}
