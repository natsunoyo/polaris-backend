package com.jobportal.polaris_backend.dto;

import com.jobportal.polaris_backend.entity.ProfileEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Base64;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDTO {
    private Long id;
    private String email;
    private String jobTitle;
    private String location;
    private String eduLevel;
    private String bio;
    private String picture;
    private List<String> skills;
    private List<Education>educations;
    private List <Course> courses;

    public ProfileEntity toEntity() {
        return new ProfileEntity(this.id, this.email, this.jobTitle, this.location, this.eduLevel,
                this.bio, this.picture!=null? Base64.getDecoder().decode(this.picture):null, this.skills, this.educations, this.courses);
    }
}
