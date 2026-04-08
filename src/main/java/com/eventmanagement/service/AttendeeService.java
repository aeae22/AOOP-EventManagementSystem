package com.eventmanagement.service;

import com.eventmanagement.model.Attendee;
import com.eventmanagement.repository.AttendeeRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AttendeeService {

    private final AttendeeRepository attendeeRepository;

    public AttendeeService(AttendeeRepository attendeeRepository) {
        this.attendeeRepository = attendeeRepository;
    }

    public List<Attendee> getAllAttendees() {
        return attendeeRepository.findAll();
    }

    public Attendee getAttendeeById(Long id) {
        return attendeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendee not found with id: " + id));
    }

    public Attendee getAttendeeByEmail(String email) {
        return attendeeRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new RuntimeException("Attendee not found with email: " + email));
    }

    public Attendee createAttendee(Attendee attendee) {
        if (attendee.getName() == null || attendee.getName().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (attendee.getEmail() == null || attendee.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
        return attendeeRepository.save(attendee);
    }

    public Attendee updateAttendee(Long id, Attendee updatedAttendee) {
        Attendee existing = getAttendeeById(id);
        existing.setName(updatedAttendee.getName());
        existing.setEmail(updatedAttendee.getEmail());
        existing.setAddress(updatedAttendee.getAddress());
        return attendeeRepository.save(existing);
    }

    public void deleteAttendee(Long id) {
        attendeeRepository.deleteById(id);
    }
}