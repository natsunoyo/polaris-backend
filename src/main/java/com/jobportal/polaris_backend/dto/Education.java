package com.jobportal.polaris_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Education {
    private String eduInst;
    private String speciality;
    private String degree;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Boolean studying;

}
