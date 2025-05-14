package com.jobportal.polaris_backend.dto;


import com.jobportal.polaris_backend.entity.JobEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobDTO {
    private Long id;
    private String jobTitle;
    private String company;
    private List<Applicant> applicants;
    private String brief;
    private String description;
    private String eduLevelRequired;
    private String location;
    private String jobType;
    private Long salaryOffered;
    private LocalDateTime postTime;
    private List<String> skillsRequired;
    private JobStatus jobStatus;

    public JobEntity toEntity() {
        return new JobEntity(this.id, this.jobTitle, this.company, this.applicants, this.brief,
                this.description, this.eduLevelRequired, this.location, this.jobType, this.salaryOffered, this.postTime,
                this.skillsRequired, this.jobStatus);
    }

}
