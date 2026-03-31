package controller;

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

    public EventOrder checkout(Attendee attendee, TicketCart cart, String orderDate) {
        attendeeController.addAttendee(attendee);

        EventOrder order = new EventOrder(attendee, orderDate);

        for (CartItem item : cart.getItems()) {
            Event event = item.getEvent();

            Ticket ticket = new Ticket(
                    item.getEvent(),
                    item.getTicketType(),
                    item.getQuantity()
            );

            order.addTicket(ticket);
            event.addAttendee(attendee);
        }

        order.calculateTotal();
        return order;
    }
}