package com.saloon.saloon_booking.controller;


import com.saloon.saloon_booking.model.Appointment;
import com.saloon.saloon_booking.model.AppointmentStatus;
import com.saloon.saloon_booking.repository.AppointmentRepository;
import com.saloon.saloon_booking.repository.ServiceRepository;
import com.saloon.saloon_booking.repository.StylistRepository;
import com.saloon.saloon_booking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;
    private final StylistRepository stylistRepository;
    private final ServiceRepository serviceRepository;

    // 🔸 Customer books appointment
    @PostMapping("/book")
    public ResponseEntity<String> bookAppointment(@RequestBody Appointment appointment) {
        appointment.setStatus(AppointmentStatus.PENDING);
        appointmentRepository.save(appointment);
        return ResponseEntity.ok("Appointment booked successfully and waiting for stylist approval.");
    }

    // 🔸 Stylist accepts/rejects appointment
    @PutMapping("/{id}/update-status")
    public ResponseEntity<String> updateStatus(@PathVariable Long id, @RequestParam AppointmentStatus status) {
        Optional<Appointment> optional = appointmentRepository.findById(id);
        if (optional.isPresent()) {
            Appointment appointment = optional.get();
            appointment.setStatus(status);
            appointmentRepository.save(appointment);
            return ResponseEntity.ok("Appointment status updated to " + status);
        }
        return ResponseEntity.notFound().build();
    }
    // 🔸 View all appointments (for Admin/Stylist)
    @GetMapping("/all")
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    // 🔸 View appointments by customer
    @GetMapping("/customer/{customerId}")
    public List<Appointment> getAppointmentsByCustomer(@PathVariable Long customerId) {
        return userRepository.findById(customerId)
                .map(appointmentRepository::findByCustomer)
                .orElse(List.of());
    }

    // 🔸 View appointments by stylist
    @GetMapping("/stylist/{stylistId}")
    public List<Appointment> getAppointmentsByStylist(@PathVariable Long stylistId) {
        return stylistRepository.findById(stylistId)
                .map(appointmentRepository::findByStylist)
                .orElse(List.of());
    }

}
