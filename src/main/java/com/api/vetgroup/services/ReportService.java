package com.api.vetgroup.services;

import com.api.vetgroup.models.Report;
import com.api.vetgroup.models.enums.ReportTypes;
import com.api.vetgroup.repositories.ReportRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportService {

    @Autowired
    private ReportRepository repository;

    public List<Report> findAll() {return repository.findAll(Sort.by(Sort.Direction.DESC, "id"));}

    public Report findById(Long id) {
        Optional<Report> obj = repository.findById(id);
        return obj.get();
    }

    @Transactional
    public Report insert(Report newReport) {
        return repository.save(newReport);
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public void update(Report report) {
        repository.save(report);
    }

    public void setApprovedReport(Long id, Boolean approved) {
        Report report = findById(id);
        if (report.getType() == ReportTypes.REPORT) {
            throw new IllegalArgumentException("Report with type of REPORT can't be approved!");
        }

        if (report.getApproved()) {
            throw new IllegalArgumentException("This Report is already approved!");
        }

        report.setApproved(approved);
        update(report);
    }

    public List<Report> findReportByStaffId(Long id) {
        return repository.findReportByStaffId(id);
    }


}
