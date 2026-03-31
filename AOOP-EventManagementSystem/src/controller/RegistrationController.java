package controller;

import exception.EventFullException;
import model.Attendee;
import model.CartItem;
import model.Event;
import model.EventOrder;
import model.Ticket;
import model.TicketCart;

public class RegistrationController {
    private EventController eventController;
    private AttendeeController attendeeController;

    public RegistrationController(EventController eventController, AttendeeController attendeeController) {
        this.eventController = eventController;
        this.attendeeController = attendeeController;
    }

    public EventOrder checkout(Attendee attendee, TicketCart cart, String orderDate) throws EventFullException {
        attendeeController.addAttendee(attendee);

        EventOrder order = new EventOrder(attendee, orderDate);

        for (CartItem item : cart.getItems()) {
            Event event = item.getEvent();

            if (event.getAttendees().size() >= event.getCapacity()) {
                throw new EventFullException("Cannot register. Event is full: " + event.getTitle());
            }

            Ticket ticket = new Ticket(event, item.getTicketType(), item.getQuantity());

            order.addTicket(ticket);
            event.addAttendee(attendee);
        }

        order.calculateTotal();
        return order;
    }
}