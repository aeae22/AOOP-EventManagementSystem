import model.*;

public class Main {
    public static void main(String[] args) {
        Event event = new Event("Tech Conference", "2026-04-10", 100.0, 3);

        TicketType regular = new TicketType("Regular", 1.0);
        TicketType vip = new TicketType("VIP", 1.5);

        event.addTicketType(regular);
        event.addTicketType(vip);

        Attendee attendee = new Attendee(
                "Alice Johnson",
                "alice@example.com",
                "123 Main Street"
        );

        TicketCart cart = new TicketCart();
        cart.addTicket(event, regular, 2);
        cart.addTicket(event, vip, 1);

        System.out.println(event);
        System.out.println(attendee);
        System.out.println(cart);

        EventOrder order = new EventOrder(attendee, "2026-03-19");

        for (CartItem item : cart.getItems()) {
            Ticket ticket = new Ticket(
                    item.getEvent(),
                    item.getTicketType(),
                    item.getQuantity()
            );
            order.addTicket(ticket);
        }

        order.calculateTotal();

        System.out.println(order);

        for (Ticket ticket : order.getTickets()) {
            System.out.println(ticket);
        }

        System.out.println("Total events created: " + Event.getTotalEvents());
    }
}
