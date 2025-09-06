package com.saloon.saloon_booking.dto;

import lombok.Data;

@Data
public class BookingRequest {
    private Long userId;
    private Long serviceId;
    private String status;
}
