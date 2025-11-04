package com.example.Userresigeration.service;

import com.example.Userresigeration.model.User;
import com.example.Userresigeration.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public User registerFarmer(String name, String email, String password) {
        logger.info("Registering new user with email: {}", email);
        try {

            if (userRepository.existsByEmail(email)) {
                logger.warn("Registration failed: email {} already exists", email);
                throw new RuntimeException("Email already registered");
            }

            User newUser = new User();
            newUser.setName(name);
            newUser.setEmail(email);
            newUser.setPassword(password); // NOTE: ideally encode passwords before saving

            User savedUser = userRepository.save(newUser);
            logger.info("User {} registered successfully", email);
            return savedUser;

        } catch (Exception e) {
            logger.error("Error registering user with email {}", email, e);
            throw new RuntimeException("User registration failed", e);
        }
    }
}
