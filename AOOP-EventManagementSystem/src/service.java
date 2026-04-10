package service;

import model.Event;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {
   
    
    private List<Event> eventDatabase = new ArrayList<>();

    public List<Event> getAllEvents() {
        return eventDatabase;
    }

    public void addEvent(Event event) {
        
        eventDatabase.add(event);
    }

    public void deleteEvent(int id) {
        eventDatabase.removeIf(e -> e.getId() == id);
    }
}
