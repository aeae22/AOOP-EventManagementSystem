import java.util.ArrayList;

public class Event {
    private String title;
    private String date;
    private double basePrice;
    private ArrayList<TicketType> ticketTypes;
    private static int totalEvents = 0;
    private int capacity;
    private ArrayList<Attendee> attendees;

    public Event(String title, String date, double basePrice, int capacity) {
        this.title = title;
        this.date = date;
        this.basePrice = basePrice;
        this.capacity = capacity;
        this.ticketTypes = new ArrayList<>();
        this.attendees = new ArrayList<>();
        totalEvents++;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public ArrayList<Attendee> getAttendees() {
        return attendees;
    }

    public void addAttendee(Attendee attendee) {
        attendees.add(attendee);
    }

    public void addTicketType(TicketType type) {
        ticketTypes.add(type);
    }

    public ArrayList<TicketType> getTicketTypes() {
        return ticketTypes;
    }

    public static int getTotalEvents() {
        return totalEvents;
    }

    @Override
    public String toString() {
        return "Event{" +
                "title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", basePrice=" + basePrice +
                ", capacity=" + capacity +
                ", attendees=" + attendees.size() +
                ", ticketTypes=" + ticketTypes.size() +
                '}';
    }
}