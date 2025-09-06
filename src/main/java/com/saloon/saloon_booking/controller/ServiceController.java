package com.saloon.saloon_booking.controller;

import com.saloon.saloon_booking.model.ServiceEntity;
import com.saloon.saloon_booking.repository.ServiceRepository;
import com.saloon.saloon_booking.repository.StylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/services")
@CrossOrigin(origins = "http://localhost:5173") // frontend URL
public class ServiceController {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private StylistRepository stylistRepository;

    // 🔹 1. Add Service with Stylist
    @PostMapping("/add/{stylistId}")
    public ServiceEntity addService(@RequestBody ServiceEntity service, @PathVariable Long stylistId) {
        service.setStylist(stylistRepository.findById(stylistId).orElseThrow());
        return serviceRepository.save(service);
    }

    // 🔹 2. Get All Services
    @GetMapping
    public List<ServiceEntity> getAllServices() {
        return serviceRepository.findAll();
    }

    // 🔹 3. Get Service by ID
    @GetMapping("/{id}")
    public ResponseEntity<ServiceEntity> getServiceById(@PathVariable Long id) {
        Optional<ServiceEntity> service = serviceRepository.findById(id);
        return service.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 🔹 4. Update Service by ID
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateService(@PathVariable Long id, @RequestBody ServiceEntity updatedService) {
        return serviceRepository.findById(id).map(service -> {
            service.setServiceName(updatedService.getServiceName());
            service.setPrice(updatedService.getPrice());
            serviceRepository.save(service);
            return ResponseEntity.ok("Service updated successfully!");
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 🔹 5. Delete Service by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteService(@PathVariable Long id) {
        if (serviceRepository.existsById(id)) {
            serviceRepository.deleteById(id);
            return ResponseEntity.ok("Service deleted successfully!");
        }
        return ResponseEntity.notFound().build();
    }
}
