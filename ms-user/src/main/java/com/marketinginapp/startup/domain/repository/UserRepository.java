package com.marketinginapp.startup.domain.repository;

import com.marketinginapp.startup.domain.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    // back and refactory
    Optional<User> findFirstByUsernameAndStatus(String username, String status);
    Optional<User> findByEmailAndStatus(String email, String status);
    Optional<User> findFirstByUsernameOrEmailAndStatus(String username, String email, String status);
    Optional<User> findByEmail(String email);
}
