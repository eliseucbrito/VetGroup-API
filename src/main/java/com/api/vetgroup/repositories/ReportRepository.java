package com.api.vetgroup.repositories;

import com.api.vetgroup.models.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
}
