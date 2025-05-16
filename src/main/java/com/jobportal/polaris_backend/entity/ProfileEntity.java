package com.jobportal.polaris_backend.entity;

import com.jobportal.polaris_backend.dto.Course;
import com.jobportal.polaris_backend.dto.Education;
import com.jobportal.polaris_backend.dto.ProfileDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Base64;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "profiles")
public class ProfileEntity {
    @Id
    private Long id;
    private String name;
    private String email;
    private String jobTitle;
    private String location;
    private String eduLevel;
    private String bio;
    private byte[] picture;
    private List<String> skills;
    private List<Education> educations;
    private List<Course> courses;
    private List<Long>savedJobs;

    public ProfileDTO toDTO() {
        return new ProfileDTO(this.id, this.name,this.email, this.jobTitle, this.location, this.eduLevel,
                this.bio, this.picture!=null? Base64.getEncoder().encodeToString(this.picture):null,
                this.skills, this.educations, this.courses, this.savedJobs);
    }
}
