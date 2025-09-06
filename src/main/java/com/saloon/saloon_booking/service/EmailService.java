package com.saloon.saloon_booking.service;

import com.saloon.saloon_booking.model.BookingEntity;
import com.saloon.saloon_booking.model.ServiceEntity;
import com.saloon.saloon_booking.model.Stylist;
import com.saloon.saloon_booking.model.User;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private static final Logger log = LoggerFactory.getLogger(EmailService.class);

    private final JavaMailSender mailSender;

    @Value("${app.mail.from:no-reply@saloon-booking.local}")
    private String from;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    public void sendBookingEmailToStylist(BookingEntity booking) {
        try {

            ServiceEntity service = booking.getService();
            User customer = booking.getCustomer();
            Stylist stylist = service != null ? service.getStylist() : null;

            if (stylist == null || stylist.getEmail() == null || stylist.getEmail().isBlank()) {
                log.warn("No stylist email configured; skipping email for booking {}", booking.getId());
                return;
            }

            String subject = "New booking from " + (customer != null ? customer.getName() : "a customer");
            String body = """
                    Hi %s,

                    You have a new booking.

                    Customer : %s
                    Service  : %s
                    When     : %s
                    Booking# : %d

                    -- Saloon Booking System
                    """.formatted(
                    stylist.getName() != null ? stylist.getName() : "Stylist",
                    customer != null ? customer.getName() : "N/A",
                    service != null ? (service.getServiceName() != null ? service.getServiceName() : service.getName()) : "N/A",
                    booking.getBookingTime() != null ? booking.getBookingTime().toString() : "Not specified",
                    booking.getId() != null ? booking.getId() : 0
            );

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, false);
            helper.setFrom("harikaraneaster@gmail.com");
            helper.setTo(stylist.getEmail());
            helper.setSubject(subject);
            helper.setText(body, false);

            mailSender.send(message);
            log.info("Email sent to stylist {} for booking {}", stylist.getEmail(), booking.getId());
        } catch (Exception ex) {
            log.error("Failed to send email for booking {}", booking.getId(), ex);
        }
    }
}
