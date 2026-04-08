package com.eventmanagement.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "ticket_type_id")
    private TicketType ticketType;

    @ManyToOne
    @JoinColumn(name = "event_order_id")
    private EventOrder eventOrder;

    public Ticket() {}

    public Ticket(Event event, TicketType ticketType, int quantity, EventOrder eventOrder) {
        this.event = event;
        this.ticketType = ticketType;
        this.quantity = quantity;
        this.eventOrder = eventOrder;
    }

    public Long getId() { return id; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public Event getEvent() { return event; }
    public TicketType getTicketType() { return ticketType; }
    public EventOrder getEventOrder() { return eventOrder; }
    public double calculateItemTotal() {
        return ticketType.calculatePrice(event.getBasePrice()) * quantity;
    }
}