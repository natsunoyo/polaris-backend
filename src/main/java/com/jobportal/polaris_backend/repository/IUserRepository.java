package com.jobportal.polaris_backend.repository;

import com.jobportal.polaris_backend.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface IUserRepository extends MongoRepository<UserEntity, Long> {
    public Optional<UserEntity> findByEmail(String email);

}
