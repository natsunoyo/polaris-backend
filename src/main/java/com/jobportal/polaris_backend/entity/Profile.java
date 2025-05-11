package com.jobportal.polaris_backend.entity;

import com.jobportal.polaris_backend.dto.Course;
import com.jobportal.polaris_backend.dto.Education;
import com.jobportal.polaris_backend.dto.ProfileDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "profiles")
public class Profile {
    @Id
    private Long id;
    private String email;
    private String jobTitle;
    private String location;
    private String bio;
    private List<String> skills;
    private List<Education> educations;
    private List<Course> courses;
    public ProfileDTO toDTO() {
        return new ProfileDTO(this.id, this.email, this.jobTitle, this.location,
                this.bio, this.skills, this.educations, this.courses);
    }
}
