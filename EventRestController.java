package controller;

import model.Event;
import service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events") 
public class EventRestController {

    @Autowired
    private EventService eventService; 

  
    @GetMapping
    public List<Event> getAll() {
        return eventService.getAllEvents();
    }

  
    @PostMapping
    public void create(@RequestBody Event event) {
        eventService.addEvent(event);
    }

   
    @DeleteMapping("/{id}")
    public void remove(@PathVariable int id) {
        eventService.deleteEvent(id);
    }
}
