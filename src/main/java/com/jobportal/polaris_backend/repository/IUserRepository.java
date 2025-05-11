package com.jobportal.polaris_backend.repository;

import com.jobportal.polaris_backend.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface IUserRepository extends MongoRepository<User, Long> {
    public Optional<User> findByEmail(String email);

}
