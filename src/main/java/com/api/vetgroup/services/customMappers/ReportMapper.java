package com.api.vetgroup.services.customMappers;

import com.api.vetgroup.dtos.create.ReportCreateDto;
import com.api.vetgroup.dtos.response.ReportResponseDto;
import com.api.vetgroup.dtos.StaffReducedDto;
import com.api.vetgroup.models.Report;
import com.api.vetgroup.models.StaffUser;
import com.api.vetgroup.services.StaffUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportMapper {

    @Autowired
    private StaffMapper staffMapper;

    @Autowired
    private StaffUserService staffService;

    public ReportResponseDto convertReportToDto(Report report) {
        try {
            ReportResponseDto reportDto = new ReportResponseDto();
            StaffReducedDto staff = staffMapper.convertStaffToReducedDto(report.getStaff());
            BeanUtils.copyProperties(report, reportDto);

            reportDto.setType(report.getType());
            reportDto.setStaff(staff);

            return reportDto;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Report convertDtoToReport(ReportCreateDto reportDto, String jwt) {
        try {
            Report report = new Report();
            BeanUtils.copyProperties(reportDto, report);

            StaffUser staff = staffService.findByJwt(jwt);

            report.setType(reportDto.getType());
            report.setStaff(staff);

            return report;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<ReportResponseDto> convertListToDto(List<Report> list) {
        try {
            List<ReportResponseDto> listDto = new ArrayList<ReportResponseDto>();

            for(Report report : list) {
                listDto.add(convertReportToDto(report));
            }

            return listDto;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
