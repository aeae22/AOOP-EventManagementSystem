import java.util.ArrayList;

public class EventOrder {
    private int orderNumber;
    private Attendee attendee;
    private ArrayList<Ticket> tickets;
    private String orderDate;
    private double totalCost;
    private static int nextOrderNumber = 1;

    public EventOrder(Attendee attendee, String orderDate) {
        this.orderNumber = nextOrderNumber++;
        this.attendee = attendee;
        this.orderDate = orderDate;
        this.tickets = new ArrayList<>();
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public Attendee getAttendee() {
        return attendee;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public void calculateTotal() {
        double total = 0.0;
        for (Ticket t : tickets) {
            total += t.calculateItemTotal();
        }
        this.totalCost = total;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    @Override
    public String toString() {
        return "EventOrder{" +
                "orderNumber=" + orderNumber +
                ", attendee=" + attendee.getName() +
                ", orderDate='" + orderDate + '\'' +
                ", totalCost=" + totalCost +
                ", tickets=" + tickets.size() +
                '}';
    }
}
