package com.eventmanagement.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String date;
    private double basePrice;
    private int capacity;

    @JsonManagedReference("event-tickettype")
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<TicketType> ticketTypes = new ArrayList<>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "event_attendee",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "attendee_id")
    )
    private List<Attendee> attendees = new ArrayList<>();

    public Event() {}

    public Event(String title, String date, double basePrice, int capacity) {
        this.title = title;
        this.date = date;
        this.basePrice = basePrice;
        this.capacity = capacity;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public double getBasePrice() { return basePrice; }
    public void setBasePrice(double basePrice) { this.basePrice = basePrice; }
    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
    public List<TicketType> getTicketTypes() { return ticketTypes; }
    public List<Attendee> getAttendees() { return attendees; }
    public void addAttendee(Attendee attendee) { attendees.add(attendee); }
}
