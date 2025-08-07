package com.saloon.saloon_booking.repository;

import com.saloon.saloon_booking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Find user by email
    Optional<User> findByEmail(String email);

    // Optional: find user by email & password for login
    Optional<User> findByEmailAndPassword(String email, String password);
}
