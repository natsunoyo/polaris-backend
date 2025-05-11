package com.jobportal.polaris_backend.dto;

import com.jobportal.polaris_backend.entity.Profile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDTO {
    private Long id;
    private String email;
    private String jobTitle;
    private String location;
    private String bio;
    private List<String> skills;
    private List<Education>educations;
    private List <Course> courses;

    public Profile toEntity() {
        return new Profile(this.id, this.email, this.jobTitle, this.location,
                this.bio, this.skills, this.educations, this.courses);
    }
}
