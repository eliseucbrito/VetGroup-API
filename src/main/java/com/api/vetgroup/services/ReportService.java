package com.api.vetgroup.services;

import com.api.vetgroup.models.Report;
import com.api.vetgroup.models.enums.ReportTypes;
import com.api.vetgroup.repositories.ReportRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportService {

    @Autowired
    private ReportRepository repository;

    public List<Report> findAll() {return repository.findAll();}

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

    @Transactional
    public void requestStatus(Report report, Boolean approved) {
        if (approved) {
            report.setType(ReportTypes.APPROVED);
            report.setApproved(true);
        } else {
            report.setType(ReportTypes.REJECTED);
            report.setApproved(false);
        }
        update(report);
    }
}
