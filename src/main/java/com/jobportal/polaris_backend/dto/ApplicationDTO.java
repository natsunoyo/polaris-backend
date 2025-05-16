package com.jobportal.polaris_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationDTO {
    private Long id;
    private Long applicantID;
    private LocalDateTime interviewTime;
    private ApplicationStatus applicationStatus;
}
