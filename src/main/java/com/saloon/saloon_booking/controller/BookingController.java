package com.saloon.saloon_booking.controller;

import com.saloon.saloon_booking.dto.BookingRequest;
import com.saloon.saloon_booking.model.BookingEntity;
import com.saloon.saloon_booking.model.ServiceEntity;
import com.saloon.saloon_booking.model.User;
import com.saloon.saloon_booking.repository.BookingRepository;
import com.saloon.saloon_booking.repository.ServiceRepository;
import com.saloon.saloon_booking.repository.UserRepository;
import com.saloon.saloon_booking.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5173"})
public class BookingController {

    private final BookingRepository bookingRepository;
    private final ServiceRepository serviceRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;

    // Create booking
    @PostMapping("/create")
    public ResponseEntity<?> createBooking(@RequestBody BookingRequest request) {
        try {
            ServiceEntity service = serviceRepository.findById(request.getServiceId())
                    .orElseThrow(() -> new RuntimeException("Service not found"));

            if (service.getStylist() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Selected service has no assigned stylist");
            }

            User customer = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            BookingEntity booking = new BookingEntity();
            booking.setService(service);
            booking.setCustomer(customer);
            booking.setStylist(service.getStylist()); // Assign stylist from service
            booking.setStatus(request.getStatus() != null ? request.getStatus() : "PENDING");
            booking.setBookingTime(LocalDateTime.now());

            BookingEntity savedBooking = bookingRepository.save(booking);

            // Send email notification
            emailService.sendBookingEmailToStylist(savedBooking);

            return ResponseEntity.ok(savedBooking);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error creating booking: " + e.getMessage());
        }
    }

    // Get all bookings
    @GetMapping
    public List<BookingEntity> getAllBookings() {
        return bookingRepository.findAll();
    }

    // Get bookings for a specific user
    @GetMapping("/user/{userId}")
    public List<BookingEntity> getUserBookings(@PathVariable Long userId) {
        return bookingRepository.findAll()
                .stream()
                .filter(b -> b.getCustomer() != null && b.getCustomer().getId().equals(userId))
                .toList();
    }



    // Get bookings for a stylist (null-safe)
    @GetMapping("/stylist/{stylistId}")
    public List<BookingEntity> getBookingsForStylist(@PathVariable Long stylistId) {
        return bookingRepository.findAll()
                .stream()
                .filter(b -> b.getStylist() != null && b.getStylist().getId().equals(stylistId))
                .toList();
    }

    // Update booking status
    @PatchMapping("/update-status/{bookingId}")
    public ResponseEntity<?> updateBookingStatus(@PathVariable Long bookingId,
                                                 @RequestParam String status) {
        try {
            BookingEntity booking = bookingRepository.findById(bookingId)
                    .orElseThrow(() -> new RuntimeException("Booking not found"));

            booking.setStatus(status);
            BookingEntity updated = bookingRepository.save(booking);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error updating status: " + e.getMessage());
        }
    }
}
