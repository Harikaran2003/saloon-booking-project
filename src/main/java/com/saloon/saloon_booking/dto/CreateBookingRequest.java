package com.saloon.saloon_booking.dto;

import java.time.LocalDateTime;

public class CreateBookingRequest {
    private Long userId;
    private Long serviceId;
    private String status;             // optional
    private LocalDateTime when;        // optional

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getServiceId() { return serviceId; }
    public void setServiceId(Long serviceId) { this.serviceId = serviceId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getWhen() { return when; }
    public void setWhen(LocalDateTime when) { this.when = when; }
}
