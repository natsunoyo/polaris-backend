package com.jobportal.polaris_backend.dto;

import com.jobportal.polaris_backend.entity.ApplicantEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Base64;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicantDTO {
    private Long applicantID;
    private String name;
    private String email;
    private Long phone;
    private String website;
    private String resume;
    private String coverLetter;
    private LocalDateTime timestamp;
    private ApplicationStatus applicationStatus;
    private LocalDateTime interviewTime;

    public ApplicantEntity toEntity() {
        return new ApplicantEntity(this.applicantID, this.name, this.email,
                this.phone, this.website, this.resume!=null? Base64.getDecoder().decode(this.resume):null,
                this.coverLetter, this.timestamp, this.applicationStatus, this.interviewTime);
    }
}
