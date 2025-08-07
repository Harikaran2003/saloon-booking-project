package com.saloon.saloon_booking.controller;

import com.saloon.saloon_booking.dto.BookingRequest;
import com.saloon.saloon_booking.model.BookingEntity;
import com.saloon.saloon_booking.model.ServiceEntity;
import com.saloon.saloon_booking.model.User;
import com.saloon.saloon_booking.repository.BookingRepository;
import com.saloon.saloon_booking.repository.ServiceRepository;
import com.saloon.saloon_booking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173") // ✅ Allow frontend access
public class BookingController {

    private final BookingRepository bookingRepository;
    private final ServiceRepository serviceRepository;
    private final UserRepository userRepository;

    // ✅ Create booking using DTO
    @PostMapping("/create")
    public BookingEntity createBooking(@RequestBody BookingRequest request) {
        ServiceEntity service = serviceRepository.findById(request.getServiceId()).orElseThrow();
        User customer = userRepository.findById(request.getUserId()).orElseThrow();

        BookingEntity booking = new BookingEntity();
        booking.setService(service);
        booking.setCustomer(customer);
        booking.setStatus(request.getStatus() != null ? request.getStatus() : "PENDING");
        booking.setBookingTime(LocalDateTime.now());

        return bookingRepository.save(booking);
    }

    // ✅ Fetch all bookings
    @GetMapping
    public List<BookingEntity> getAllBookings() {
        return bookingRepository.findAll();
    }

    // ✅ Fetch bookings by user
    @GetMapping("/user/{userId}")
    public List<BookingEntity> getUserBookings(@PathVariable Long userId) {
        return bookingRepository.findAll()
                .stream()
                .filter(b -> b.getCustomer().getId().equals(userId))
                .toList();
    }
}
