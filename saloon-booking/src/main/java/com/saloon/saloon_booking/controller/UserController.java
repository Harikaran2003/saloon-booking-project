package com.saloon.saloon_booking.controller;

import com.saloon.saloon_booking.model.User;
import com.saloon.saloon_booking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173") // ✅ Allow React frontend
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // 1️⃣ Create a new user (Signup)
    @PostMapping
    public User createUser(@RequestBody User user) {
        System.out.println("Signup request: " + user.getEmail() + " | " + user.getPassword());
        return userRepository.save(user);
    }


    // 2️⃣ Get all users
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // 3️⃣ Login endpoint (returns JSON instead of exception)
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User loginRequest) {
        return userRepository.findByEmailAndPassword(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                ).<ResponseEntity<?>>map(user -> ResponseEntity.ok(user))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials"));
    }



    // 4️⃣ Optional: Handle GET request to /login to avoid 405
    @GetMapping("/login")
    public String loginInfo() {
        return "Use POST method to login!";
    }
}
