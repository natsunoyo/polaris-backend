package com.jobportal.polaris_backend.repository;

import com.jobportal.polaris_backend.entity.JobEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IJobRepository extends MongoRepository<JobEntity, Long> {
public List<JobEntity>findByPostedBy(long postedBy);
}
