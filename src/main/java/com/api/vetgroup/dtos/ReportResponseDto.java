package com.api.vetgroup.dtos;

import com.api.vetgroup.models.StaffUser;

import java.time.LocalDateTime;

public class ReportResponseDto {

    private Long id;
    private String title;
    private String description;
    private Double payment_value;
    private Integer type;
    private LocalDateTime created_at;
    private Boolean approved;
    private StaffReducedDto staff;
}
