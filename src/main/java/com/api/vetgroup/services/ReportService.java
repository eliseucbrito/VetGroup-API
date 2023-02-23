package com.api.vetgroup.services;

import com.api.vetgroup.models.Report;
import com.api.vetgroup.models.StaffUser;
import com.api.vetgroup.models.enums.ReportTypes;
import com.api.vetgroup.repositories.ReportRepository;
import com.api.vetgroup.services.customMappers.ReportMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ReportService {

    @Autowired
    private ReportRepository repository;

    @Autowired
    private StaffUserService staffService;

    public List<Report> findAll(String sort_by, String direction) {
        var dir = Objects.equals(direction.toUpperCase(), "ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;
        return  repository.findAll(Sort.by(dir, sort_by));
    }

    public Report findById(Long id) {
        Optional<Report> obj = repository.findById(id);

        if(obj.isEmpty()) {
            throw new IllegalArgumentException("This report not exists");
        }

        return obj.get();
    }

    @Transactional
    public Report insert(Report newReport) {
        if (newReport.getType() != ReportTypes.PAYMENT) {
            newReport.setPaymentValue(null);
        }
        if (newReport.getType() == ReportTypes.PAYMENT && newReport.getPaymentValue() == null) {
            throw new IllegalArgumentException("Report type payment cannot have a null payment_value");
        }
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

    public void setApprovedReport(Long id, Boolean approved, String authorization) {
        Report report = findById(id);
        StaffUser approvedBy = staffService.findByJwt(authorization);

        if (report.getType() == ReportTypes.REPORT) {
            throw new IllegalArgumentException("Report with type of REPORT can't be approved!");
        }

        if (report.getApproved() != null) {
            if (report.getApproved()) {
                throw new IllegalArgumentException("This Report is already approved!");
            }
            throw new IllegalArgumentException("This Report is already was denied!");
        }

        report.setApprovedBy(approvedBy);
        report.setApproved(approved);
        update(report);
    }

    public List<Report> findReportByStaffId(Long id) {
        return repository.findReportByStaffId(id);
    }


}
