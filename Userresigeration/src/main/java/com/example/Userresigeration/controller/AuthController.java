package com.example.Userresigeration.controller;

import com.example.Userresigeration.service.CustomOAuth2UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final CustomOAuth2UserService customOAuth2UserService;

    public AuthController(CustomOAuth2UserService customOAuth2UserService) {
        this.customOAuth2UserService = customOAuth2UserService;
    }

    @GetMapping("/success")
    public ResponseEntity<String> loginSuccess(OAuth2User user) {
        logger.info("OAuth2 login successful for user: {}", user.getAttribute("email"));
        customOAuth2UserService.handleOAuthLogin(user);
        return ResponseEntity.ok("Login successful");
    }

    @GetMapping("/failure")
    public ResponseEntity<String> loginFailure() {
        logger.warn("OAuth2 login failed");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Login failed. Please try again.");
    }
}
