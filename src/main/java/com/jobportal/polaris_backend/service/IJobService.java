package com.jobportal.polaris_backend.service;

import com.jobportal.polaris_backend.dto.ApplicantDTO;
import com.jobportal.polaris_backend.dto.ApplicationDTO;
import com.jobportal.polaris_backend.dto.JobDTO;
import com.jobportal.polaris_backend.exception.JobPortalException;

import java.util.List;

public interface IJobService {
    JobDTO postJob(JobDTO jobDTO) throws JobPortalException;

    List<JobDTO> getAllJobs();

    JobDTO getJob(Long id) throws JobPortalException;

    void applyJob(Long id, ApplicantDTO applicantDTO) throws JobPortalException;

    List<JobDTO> getJobsPostedBy(Long id);

    void changeApplyStatus(ApplicationDTO applicationDTO)  throws JobPortalException;
}
