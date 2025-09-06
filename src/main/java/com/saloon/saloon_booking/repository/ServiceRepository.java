package com.saloon.saloon_booking.repository;

import com.saloon.saloon_booking.model.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {
}
