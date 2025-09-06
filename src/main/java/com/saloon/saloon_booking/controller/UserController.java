package com.saloon.saloon_booking.controller;

import com.saloon.saloon_booking.model.Stylist;
import com.saloon.saloon_booking.model.User;
import com.saloon.saloon_booking.repository.StylistRepository;
import com.saloon.saloon_booking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173") // Allow React frontend
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StylistRepository stylistRepository;

    // 1️⃣ Create a new user (Signup)
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            // Check if email already exists
            if (userRepository.existsByEmail(user.getEmail())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Email already in use");
            }

            // Save user
            User savedUser = userRepository.save(user);

            // ✅ If role is STYLIST, also create Stylist entry
            if ("STYLIST".equalsIgnoreCase(savedUser.getRole())) {
                Stylist stylist = new Stylist();
                stylist.setId(savedUser.getId()); // same ID as userId
                stylist.setEmail(savedUser.getEmail());
                stylist.setAvailable(true);
                stylist.setPhone(""); // optional, can be updated later
                stylistRepository.save(stylist);
            }

            return ResponseEntity.ok(savedUser);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error during signup: " + e.getMessage());
        }
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
