package com.saloon.saloon_booking.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name="services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private String description;
    private String serviceName;
    private Double price;
    private Double cost;

    @ManyToOne
    @JoinColumn(name = "stylist_id")
    private Stylist stylist;

}
