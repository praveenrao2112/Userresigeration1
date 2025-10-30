package com.example.Userresigeration.service;

import com.example.Userresigeration.model.User;
import com.example.Userresigeration.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service layer for handling user registration and SSO user provisioning.
 */
@Service
public class UserService {

    private final UserRepository repo;
    private final PasswordEncoder passwordEncoder;

    // ✅ Constructor injection (Spring Boot automatically injects beans)
    public UserService(UserRepository repo, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Registers a new farmer account (normal registration, not SSO).
     *
     * @param name        Farmer's name
     * @param email       Farmer's email (must be unique)
     * @param rawPassword Raw password (will be encoded)
     * @return Saved User entity
     */
    public User registerFarmer(String name, String email, String rawPassword) {
        // Check if email already exists
        if (repo.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Email already registered");
        }

        // Build new User entity
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setRole("ROLE_FARMER");
        user.setEnabled(true);
        user.setOauth2(false);

        // Save in database
        return repo.save(user);
    }

    /**
     * Creates or updates a user after OAuth2 (Google/Keycloak) login.
     * This ensures SSO users are automatically registered in the DB.
     *
     * @param name  Name from OAuth provider
     * @param email Email from OAuth provider
     * @return Saved or updated User
     */
    public User createOrUpdateOAuthUser(String name, String email) {
        Optional<User> existing = repo.findByEmail(email);

        if (existing.isPresent()) {
            User user = existing.get();
            user.setName(name);
            user.setOauth2(true);
            if (user.getRole() == null) {
                user.setRole("ROLE_FARMER");
            }
            return repo.save(user);
        } else {
            User newUser = new User();
            newUser.setName(name);
            newUser.setEmail(email);
            newUser.setPassword(null); // no password for OAuth users
            newUser.setRole("ROLE_FARMER");
            newUser.setEnabled(true);
            newUser.setOauth2(true);
            return repo.save(newUser);
        }
    }

    /**
     * Finds a user by email.
     */
    public Optional<User> findByEmail(String email) {
        return repo.findByEmail(email);
    }
}
