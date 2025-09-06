package com.saloon.saloon_booking.repository;

import com.saloon.saloon_booking.model.Stylist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StylistRepository  extends JpaRepository<Stylist,Long> {
    Stylist findByName(String name);
}
