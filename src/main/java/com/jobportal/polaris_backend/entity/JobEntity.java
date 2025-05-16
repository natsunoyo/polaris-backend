package com.jobportal.polaris_backend.entity;

import com.jobportal.polaris_backend.dto.JobDTO;
import com.jobportal.polaris_backend.dto.JobStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="jobs")
public class JobEntity {
    @Id
    private Long id;
    private String jobTitle;
    private String company;
    private List<ApplicantEntity> applicants;
    private String brief;
    private String description;
    private String eduLevelRequired;
    private String location;
    private String jobType;
    private Long salaryOffered;
    private LocalDateTime postTime;
    private List<String> skillsRequired;
    private JobStatus jobStatus;
    private Long postedBy;

    public JobDTO toDTO() {
        return new JobDTO(this.id, this.jobTitle, this.company, this.applicants!=null?this.applicants.stream().map((x) -> x.toDTO()).toList():null, this.brief,
                this.description, this.eduLevelRequired, this.location, this.jobType, this.salaryOffered, this.postTime,
                this.skillsRequired, this.jobStatus, this.postedBy);
    }

}
