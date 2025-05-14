package com.jobportal.polaris_backend.api;

import com.jobportal.polaris_backend.dto.JobDTO;
import com.jobportal.polaris_backend.dto.ProfileDTO;
import com.jobportal.polaris_backend.exception.JobPortalException;
import com.jobportal.polaris_backend.service.IJobService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@Validated
@RequestMapping("/jobs")
public class JobAPI {
    @Autowired
    private IJobService iJobService;

    @PostMapping("/post")
    public ResponseEntity<JobDTO> postJob(@RequestBody @Valid JobDTO jobDTO) throws JobPortalException {
        return new ResponseEntity<>(iJobService.postJob(jobDTO), HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<JobDTO>>getAllJobs() throws JobPortalException {
        return new ResponseEntity<>(iJobService.getAllJobs(), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<JobDTO>getJob(@PathVariable Long id) throws JobPortalException {
        return new ResponseEntity<>(iJobService.getJob(id), HttpStatus.OK);
    }

}
