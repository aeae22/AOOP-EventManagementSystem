package com.eventmanagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "attendees")
public class Attendee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String address;

    @JsonBackReference("event-attendee")
    @ManyToMany(mappedBy = "attendees")
    private List<Event> events = new ArrayList<>();

    public Attendee() {}

    public Attendee(String name, String email, String address) {
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public List<Event> getEvents() { return events; }
}