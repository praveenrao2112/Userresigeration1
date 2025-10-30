package com.example.Userresigeration.controller;

import com.example.Userresigeration.model.User;
import com.example.Userresigeration.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Controller for user registration and SSO provisioning.
 */
@RestController
@RequestMapping("/api/auth")
public class UserResigerationController {

    private final UserService userService;

    // Constructor injection (Spring Boot automatically wires the service)
    public UserResigerationController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Registers a new farmer in the system (local signup).
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegisterRequest req) {
        try {
            User saved = userService.registerFarmer(req.getName(), req.getEmail(), req.getPassword());
            return ResponseEntity.ok(Map.of(
                    "status", "success",
                    "message", "User registered successfully",
                    "email", saved.getEmail()
            ));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(Map.of(
                    "status", "error",
                    "message", ex.getMessage()
            ));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of(
                    "status", "error",
                    "message", "Unexpected error: " + e.getMessage()
            ));
        }
    }

    /**
     * Test endpoint for OAuth2/SSO login — returns the current user info.
     */
    @GetMapping("/me")
    public ResponseEntity<?> currentUser(@RequestParam(required = false) String email) {
        if (email == null || email.isBlank()) {
            return ResponseEntity.badRequest().body(Map.of(
                    "status", "error",
                    "message", "Email parameter required"
            ));
        }
        return ResponseEntity.ok(Map.of(
                "status", "ok",
                "user", email
        ));
    }
}

