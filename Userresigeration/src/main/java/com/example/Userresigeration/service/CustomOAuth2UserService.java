package com.example.Userresigeration.service;

import com.example.Userresigeration.model.User;
import com.example.Userresigeration.repository.UserRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oauth2User = super.loadUser(userRequest);

        String email = oauth2User.getAttribute("email");
        String name = oauth2User.getAttribute("name");

        if (email == null) {
            throw new IllegalArgumentException("Email not found from Google account");
        }

        Optional<User> existingUser = userRepository.findByEmail(email);

        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setName(name);
            user.setOauth2(true);
            userRepository.save(user);
        } else {
            User user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setPassword(null);
            user.setRole("ROLE_FARMER");
            user.setEnabled(true);
            user.setOauth2(true);
            userRepository.save(user);
        }

        return oauth2User;
    }
}
