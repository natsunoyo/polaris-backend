package com.jobportal.polaris_backend.repository;

import com.jobportal.polaris_backend.entity.OTPEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface IOTPRepository extends MongoRepository<OTPEntity, String> {
List<OTPEntity>findByCreationTimeBefore(LocalDateTime expiry);
}
