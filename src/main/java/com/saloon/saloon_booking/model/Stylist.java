package com.saloon.saloon_booking.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="stylists")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stylist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String phone;

    private boolean available; // true = available, false = busy

}
