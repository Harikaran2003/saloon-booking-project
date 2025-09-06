package com.saloon.saloon_booking.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users") // ✅ Explicit table name
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) // ✅ Name cannot be null
    private String name;

    @Column(nullable = false, unique = true) // ✅ Email must be unique
    private String email;

    @Column(nullable = false) // ✅ Password cannot be null
    private String password;

    @Column(nullable = false) // ✅ Role should be stored
    private String role; // e.g., CUSTOMER, ADMIN, STYLIST
}
