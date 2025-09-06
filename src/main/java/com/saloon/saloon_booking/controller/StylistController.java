package com.saloon.saloon_booking.controller;

import com.saloon.saloon_booking.model.Stylist;
import com.saloon.saloon_booking.repository.StylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stylists")
public class StylistController {
    @Autowired
    private StylistRepository stylistRepository;

    @PostMapping
    public Stylist addStylist(@RequestBody Stylist stylist) {
        return stylistRepository.save(stylist);
    }

    @GetMapping
    public List<Stylist> getAllStylists() {
        return stylistRepository.findAll();
    }

}
