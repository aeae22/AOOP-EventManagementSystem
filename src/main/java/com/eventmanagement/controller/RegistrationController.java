package com.eventmanagement.controller;

import com.eventmanagement.model.EventOrder;
import com.eventmanagement.service.RegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/registrations")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping
    public ResponseEntity<EventOrder> register(
            @RequestParam Long eventId,
            @RequestParam Long attendeeId,
            @RequestParam Long ticketTypeId,
            @RequestParam int quantity,
            @RequestParam String orderDate) {

        EventOrder order = registrationService.registerAttendee(
                eventId, attendeeId, ticketTypeId, quantity, orderDate);
        return ResponseEntity.ok(order);
    }

    @GetMapping
    public List<EventOrder> getAllOrders() {
        return registrationService.getAllOrders();
    }

    @GetMapping("/attendee/{attendeeId}")
    public List<EventOrder> getOrdersByAttendee(@PathVariable Long attendeeId) {
        return registrationService.getOrdersByAttendee(attendeeId);
    }
}