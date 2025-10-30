package com.example.Userresigeration.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AuthController {

    @GetMapping("/api/auth/success")
    public Map<String, Object> loginSuccess(@AuthenticationPrincipal OAuth2User user) {
        // This will return Google profile info as JSON
        return Map.of(
                "message", "Login successful!",
                "name", user.getAttribute("name"),
                "email", user.getAttribute("email"),
                "picture", user.getAttribute("picture")
        );
    }
}
