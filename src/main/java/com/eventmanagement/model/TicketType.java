package com.eventmanagement.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ticket_types")
public class TicketType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double priceMultiplier;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    public TicketType() {}

    public TicketType(String name, double priceMultiplier, Event event) {
        this.name = name;
        this.priceMultiplier = priceMultiplier;
        this.event = event;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getPriceMultiplier() { return priceMultiplier; }
    public void setPriceMultiplier(double priceMultiplier) { this.priceMultiplier = priceMultiplier; }
    public Event getEvent() { return event; }
    public void setEvent(Event event) { this.event = event; }
    public double calculatePrice(double basePrice) { return basePrice * priceMultiplier; }
}