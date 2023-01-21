package com.api.vetgroup.repositories;

import com.api.vetgroup.models.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    @Query(value = "SELECT * FROM tb_reports as R WHERE R.staff_id = ?1 ORDER BY R.created_at DESC", nativeQuery = true)
    public List<Report> findReportByStaffId(Long id);
}
