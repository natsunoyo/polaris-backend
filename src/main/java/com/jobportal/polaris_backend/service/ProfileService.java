package com.jobportal.polaris_backend.service;

import com.jobportal.polaris_backend.dto.ProfileDTO;
import com.jobportal.polaris_backend.entity.Profile;
import com.jobportal.polaris_backend.exception.JobPortalException;
import com.jobportal.polaris_backend.repository.IProfileRepository;
import com.jobportal.polaris_backend.utility.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("profileService")
public class ProfileService implements IProfileService{

    @Autowired
    private IProfileRepository iProfileRepository;

    @Override
    public Long createProfile(String email) throws JobPortalException {
        Profile profile = new Profile();
        profile.setId(Utilities.getNextSequence("profiles"));
        profile.setEmail(email);
        profile.setSkills(new ArrayList<>());
        profile.setEducations(new ArrayList<>());
        profile.setCourses(new ArrayList<>());
        iProfileRepository.save(profile);
        return profile.getId();

    }

    @Override
    public ProfileDTO getProfile(Long id) throws JobPortalException {
        return iProfileRepository.findById(id).orElseThrow(()->new JobPortalException("PROFILE_NOT_FOUND")).toDTO();
    }

    @Override
    public ProfileDTO updateProfile(ProfileDTO profileDTO) throws JobPortalException {
        iProfileRepository.findById(profileDTO.getId()).orElseThrow(()->new JobPortalException("PROFILE_NOT_FOUND"));
        iProfileRepository.save(profileDTO.toEntity());
        return profileDTO;

    }
}
