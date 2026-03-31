package controller;

import model.Event;
import java.util.ArrayList;

public class EventController {
    private ArrayList<Event> events;

    public EventController() {
        events = new ArrayList<>();
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public Event findEventByTitle(String title) {
        for (Event event : events) {
            if (event.getTitle().equalsIgnoreCase(title)) {
                return event;
            }
        }
        return null;
    }

    public boolean removeEvent(String title) {
        Event event = findEventByTitle(title);
        if (event != null) {
            events.remove(event);
            return true;
        }
        return false;
    }
}