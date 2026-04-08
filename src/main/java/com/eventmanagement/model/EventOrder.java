package com.eventmanagement.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "event_orders")
public class EventOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderDate;
    private double totalCost;

    @ManyToOne
    @JoinColumn(name = "attendee_id")
    private Attendee attendee;

    @OneToMany(mappedBy = "eventOrder", cascade = CascadeType.ALL)
    private List<Ticket> tickets = new ArrayList<>();

    public EventOrder() {}

    public EventOrder(Attendee attendee, String orderDate) {
        this.attendee = attendee;
        this.orderDate = orderDate;
    }

    public Long getId() { return id; }
    public String getOrderDate() { return orderDate; }
    public void setOrderDate(String orderDate) { this.orderDate = orderDate; }
    public double getTotalCost() { return totalCost; }
    public void setTotalCost(double totalCost) { this.totalCost = totalCost; }
    public Attendee getAttendee() { return attendee; }
    public List<Ticket> getTickets() { return tickets; }
    public void addTicket(Ticket ticket) { tickets.add(ticket); }
    public void calculateTotal() {
        this.totalCost = tickets.stream()
                .mapToDouble(Ticket::calculateItemTotal)
                .sum();
    }
}