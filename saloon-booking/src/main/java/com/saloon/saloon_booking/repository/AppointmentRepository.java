package com.saloon.saloon_booking.repository;

import com.saloon.saloon_booking.model.Appointment;
import com.saloon.saloon_booking.model.Stylist;
import com.saloon.saloon_booking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByCustomer(User customer);
    List<Appointment> findByStylist(Stylist stylist);
}
