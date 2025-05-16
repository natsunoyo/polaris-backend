package com.jobportal.polaris_backend.entity;

import com.jobportal.polaris_backend.dto.ApplicantDTO;
import com.jobportal.polaris_backend.dto.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Base64;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicantEntity {
    private Long applicantID;
    private String name;
    private String email;
    private Long phone;
    private String website;
    private byte[] resume;
    private String coverLetter;
    private LocalDateTime timestamp;
    private ApplicationStatus applicationStatus;
    private LocalDateTime interviewTime;

    public ApplicantDTO toDTO() {
        return new ApplicantDTO(this.applicantID, this.name, this.email,
                this.phone, this.website, this.resume!=null? Base64.getEncoder().encodeToString(this.resume):null,
                this.coverLetter, this.timestamp, this.applicationStatus, this.interviewTime);
    }
}
