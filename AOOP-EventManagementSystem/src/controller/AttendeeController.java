package controller;

import model.Attendee;
import java.util.ArrayList;

public class AttendeeController {
    private ArrayList<Attendee> attendees;

    public AttendeeController() {
        attendees = new ArrayList<>();
    }

    public void addAttendee(Attendee attendee) {
        attendees.add(attendee);
    }

    public ArrayList<Attendee> getAttendees() {
        return attendees;
    }

    public Attendee findAttendeeByEmail(String email) {
        for (Attendee attendee : attendees) {
            if (attendee.getEmail().equalsIgnoreCase(email)) {
                return attendee;
            }
        }
        return null;
    }

    public boolean removeAttendee(String email) {
        Attendee attendee = findAttendeeByEmail(email);
        if (attendee != null) {
            attendees.remove(attendee);
            return true;
        }
        return false;
    }
}