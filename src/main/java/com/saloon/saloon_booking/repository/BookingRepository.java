package com.saloon.saloon_booking.repository;

import com.saloon.saloon_booking.model.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<BookingEntity, Long> {
}
