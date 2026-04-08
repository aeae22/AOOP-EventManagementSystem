package com.eventmanagement.service;

import com.eventmanagement.model.*;
import com.eventmanagement.repository.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RegistrationService {

    private final EventRepository eventRepository;
    private final AttendeeRepository attendeeRepository;
    private final EventOrderRepository eventOrderRepository;
    private final TicketTypeRepository ticketTypeRepository;

    public RegistrationService(EventRepository eventRepository,
                               AttendeeRepository attendeeRepository,
                               EventOrderRepository eventOrderRepository,
                               TicketTypeRepository ticketTypeRepository) {
        this.eventRepository = eventRepository;
        this.attendeeRepository = attendeeRepository;
        this.eventOrderRepository = eventOrderRepository;
        this.ticketTypeRepository = ticketTypeRepository;
    }

    public EventOrder registerAttendee(Long eventId, Long attendeeId,
                                       Long ticketTypeId, int quantity,
                                       String orderDate) {

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        Attendee attendee = attendeeRepository.findById(attendeeId)
                .orElseThrow(() -> new RuntimeException("Attendee not found"));

        TicketType ticketType = ticketTypeRepository.findById(ticketTypeId)
                .orElseThrow(() -> new RuntimeException("Ticket type not found"));

        if (event.getAttendees().size() >= event.getCapacity()) {
            throw new RuntimeException("Event is full: " + event.getTitle());
        }

        EventOrder order = new EventOrder(attendee, orderDate);
        Ticket ticket = new Ticket(event, ticketType, quantity, order);
        order.addTicket(ticket);
        order.calculateTotal();

        event.addAttendee(attendee);
        eventRepository.save(event);

        return eventOrderRepository.save(order);
    }

    public List<EventOrder> getOrdersByAttendee(Long attendeeId) {
        return eventOrderRepository.findByAttendeeId(attendeeId);
    }

    public List<EventOrder> getAllOrders() {
        return eventOrderRepository.findAll();
    }
}