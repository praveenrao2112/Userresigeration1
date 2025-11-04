package com.example.Userresigeration.controller;

import com.example.Userresigeration.model.User;
import com.example.Userresigeration.request.UserRegisterRequest;
import com.example.Userresigeration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserResigerationController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserRegisterRequest req) {
        User savedUser = userService.registerFarmer(req.getName(), req.getEmail(), req.getPassword());
        return ResponseEntity.ok(savedUser);
    }
}
