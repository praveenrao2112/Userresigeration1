package com.example.Userresigeration.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService {

    private static final Logger logger = LoggerFactory.getLogger(CustomOAuth2UserService.class);

    public void handleOAuthLogin(OAuth2User user) {
        if (user == null) {
            logger.warn("OAuth login attempt with null user");
            return;
        }

        String name = user.getAttribute("name");
        String email = user.getAttribute("email");
        String picture = user.getAttribute("picture");

        logger.info("OAuth login success: name={}, email={}", name, email);

    }
}
