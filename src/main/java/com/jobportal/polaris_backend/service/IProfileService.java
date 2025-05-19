package com.jobportal.polaris_backend.service;

import com.jobportal.polaris_backend.dto.ProfileDTO;
import com.jobportal.polaris_backend.exception.JobPortalException;

import java.util.List;

public interface IProfileService {
    Long createProfile(String email) throws JobPortalException;

    ProfileDTO getProfile(Long id) throws JobPortalException;

    ProfileDTO updateProfile(ProfileDTO profileDTO) throws JobPortalException;

    List<ProfileDTO> getAllProfiles();

    void updateProfileName(Long profileId, String name);
}
