package com.jobportal.polaris_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    private String programTitle;
    private String company;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
