package model;

public class CartItem {
    private Event event;
    private TicketType ticketType;
    private int quantity;

    public CartItem(Event event, TicketType ticketType, int quantity) {
        this.event = event;
        this.ticketType = ticketType;
        this.quantity = quantity;
    }

    public Event getEvent() {
        return event;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double calculateItemTotal() {
        return ticketType.calculatePrice(event.getBasePrice()) * quantity;
    }

    @Override
    public String toString() {
        return "model.CartItem{" +
                "event=" + event.getTitle() +
                ", ticketType=" + ticketType.getName() +
                ", quantity=" + quantity +
                ", total=" + calculateItemTotal() +
                '}';
    }
}
