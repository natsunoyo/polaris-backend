package com.jobportal.polaris_backend.service;

import com.jobportal.polaris_backend.dto.ProfileDTO;
import com.jobportal.polaris_backend.entity.ProfileEntity;
import com.jobportal.polaris_backend.exception.JobPortalException;
import com.jobportal.polaris_backend.repository.IProfileRepository;
import com.jobportal.polaris_backend.utility.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("profileService")
public class ProfileService implements IProfileService{

    @Autowired
    private IProfileRepository iProfileRepository;

    @Override
    public Long createProfile(String email) throws JobPortalException {
        ProfileEntity profile = new ProfileEntity();
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

    @Override
    public List<ProfileDTO> getAllProfiles() {
        return iProfileRepository.findAll().stream().map((x)->x.toDTO()).toList();
    }

    @Override
    public void updateProfileName(Long profileId, String name) {
        iProfileRepository.findById(profileId).ifPresent(profile -> {
            profile.setName(name);
            iProfileRepository.save(profile);
        });
}}
