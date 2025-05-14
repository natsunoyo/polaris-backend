package com.jobportal.polaris_backend.repository;

import com.jobportal.polaris_backend.entity.ProfileEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IProfileRepository extends MongoRepository<ProfileEntity, Long> {
}
