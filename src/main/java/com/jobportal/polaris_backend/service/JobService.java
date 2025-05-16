package com.jobportal.polaris_backend.service;

import com.jobportal.polaris_backend.dto.ApplicantDTO;
import com.jobportal.polaris_backend.dto.ApplicationDTO;
import com.jobportal.polaris_backend.dto.ApplicationStatus;
import com.jobportal.polaris_backend.dto.JobDTO;
import com.jobportal.polaris_backend.entity.ApplicantEntity;
import com.jobportal.polaris_backend.entity.JobEntity;
import com.jobportal.polaris_backend.exception.JobPortalException;
import com.jobportal.polaris_backend.repository.IJobRepository;
import com.jobportal.polaris_backend.utility.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public void applyJob(Long id, ApplicantDTO applicantDTO) throws JobPortalException {
        JobEntity job = iJobRepository.findById(id).orElseThrow(() -> new JobPortalException("JOB_NOT_FOUND"));
        List<ApplicantEntity>applicants=job.getApplicants();
        if(applicants==null)applicants=new ArrayList<>();
        if(applicants.stream().filter((x) ->x.getApplicantID()==applicantDTO.getApplicantID()).toList().size()>0) throw new JobPortalException("JOB_APPLIED_ALREADY");
        applicantDTO.setApplicationStatus(ApplicationStatus.APPLIED);
        applicants.add(applicantDTO.toEntity());
        job.setApplicants(applicants);
        iJobRepository.save(job);

    }

    @Override
    public List<JobDTO> getJobsPostedBy(Long id) {
        return iJobRepository.findByPostedBy(id).stream().map((x) -> x.toDTO()).toList();
    }

    @Override
    public void changeApplyStatus(ApplicationDTO applicationDTO) throws JobPortalException {
        JobEntity job = iJobRepository.findById(applicationDTO.getId()).orElseThrow(() -> new JobPortalException("JOB_NOT_FOUND"));
        List<ApplicantEntity>applicants=job.getApplicants().stream().map((x)->{
            if(applicationDTO.getApplicantID()==x.getApplicantID()){
                x.setApplicationStatus(applicationDTO.getApplicationStatus());
                if(applicationDTO.getApplicationStatus().equals(ApplicationStatus.INTERVIEWING)){
                    x.setInterviewTime(applicationDTO.getInterviewTime());
                }
            }
            return x;
        }).toList();
        job.setApplicants(applicants);
        iJobRepository.save(job);
    }

}
