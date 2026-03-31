package model;

import java.util.ArrayList;

public class TicketCart {
    private ArrayList<CartItem> items;

    public TicketCart() {
        this.items = new ArrayList<>();
    }

    public void addTicket(Event event, TicketType type, int quantity) {
        CartItem item = new CartItem(event, type, quantity);
        items.add(item);
    }

    public void removeTicket(Event event, TicketType type) {
        items.removeIf(i ->
                i.getEvent().equals(event) &&
                        i.getTicketType().equals(type));
    }

    public double calculateTotal() {
        double total = 0.0;
        for (CartItem item : items) {
            total += item.calculateItemTotal();
        }
        return total;
    }

    public ArrayList<CartItem> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "TicketCart{" +
                "items=" + items +
                ", total=" + calculateTotal() +
                '}';
    }
}