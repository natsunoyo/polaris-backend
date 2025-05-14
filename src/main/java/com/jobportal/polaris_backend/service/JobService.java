package com.jobportal.polaris_backend.service;

import com.jobportal.polaris_backend.dto.JobDTO;
import com.jobportal.polaris_backend.exception.JobPortalException;
import com.jobportal.polaris_backend.repository.IJobRepository;
import com.jobportal.polaris_backend.utility.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service("jobService")
public class JobService implements IJobService {
    @Autowired
    private IJobRepository iJobRepository;

    @Override
    public JobDTO postJob(JobDTO jobDTO) throws JobPortalException {
        jobDTO.setId(Utilities.getNextSequence("jobs"));
        jobDTO.setPostTime(LocalDateTime.now());
        return iJobRepository.save(jobDTO.toEntity()).toDTO();
    }

    @Override
    public List<JobDTO> getAllJobs() {
        return iJobRepository.findAll().stream().map((x) -> x.toDTO()).toList();
    }

    @Override
    public JobDTO getJob(Long id) throws JobPortalException {
        return iJobRepository.findById(id).orElseThrow(() -> new JobPortalException("JOB_NOT_FOUND")).toDTO();
    }

}
