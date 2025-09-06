package com.saloon.saloon_booking.service;

import com.saloon.saloon_booking.dto.BookingRequest;
import com.saloon.saloon_booking.model.BookingEntity;
import com.saloon.saloon_booking.model.ServiceEntity;
import com.saloon.saloon_booking.model.User;
import com.saloon.saloon_booking.repository.BookingRepository;
import com.saloon.saloon_booking.repository.ServiceRepository;
import com.saloon.saloon_booking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final ServiceRepository serviceRepository;
    private final UserRepository userRepository;

    public BookingEntity createBooking(BookingRequest request) {
        ServiceEntity service = serviceRepository.findById(request.getServiceId())
                .orElseThrow(() -> new RuntimeException("Service not found"));

        User customer = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        BookingEntity booking = new BookingEntity();
        booking.setService(service);
        booking.setCustomer(customer);
        booking.setStatus(request.getStatus() != null ? request.getStatus() : "PENDING");
        booking.setBookingTime(LocalDateTime.now());

        return bookingRepository.save(booking);
    }

    public List<BookingEntity> getAllBookings() {
        return bookingRepository.findAll();
    }

    public List<BookingEntity> getUserBookings(Long userId) {
        return bookingRepository.findAll()
                .stream()
                .filter(b -> b.getCustomer().getId().equals(userId))
                .toList();
    }
}
