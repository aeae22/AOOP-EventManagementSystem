package com.eventmanagement.service;

import com.eventmanagement.model.Event;
import com.eventmanagement.model.TicketType;
import com.eventmanagement.repository.EventRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + id));
    }

    public Event getEventByTitle(String title) {
        return eventRepository.findByTitleIgnoreCase(title)
                .orElseThrow(() -> new RuntimeException("Event not found with title: " + title));
    }

    public Event createEvent(Event event) {
        TicketType regular = new TicketType("Regular", 1.0, event);
        TicketType vip = new TicketType("VIP", 1.5, event);
        event.getTicketTypes().add(regular);
        event.getTicketTypes().add(vip);
        return eventRepository.save(event);
    }

    public Event updateEvent(Long id, Event updatedEvent) {
        Event existing = getEventById(id);
        existing.setTitle(updatedEvent.getTitle());
        existing.setDate(updatedEvent.getDate());
        existing.setBasePrice(updatedEvent.getBasePrice());
        existing.setCapacity(updatedEvent.getCapacity());
        return eventRepository.save(existing);
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }}