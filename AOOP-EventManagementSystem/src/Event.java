import java.util.ArrayList;

public class Event {
    private String title;
    private String date;
    private double basePrice;
    private ArrayList<TicketType> ticketTypes;
    private static int totalEvents = 0;

    public Event(String title, String date, double basePrice) {
        this.title = title;
        this.date = date;
        this.basePrice = basePrice;
        this.ticketTypes = new ArrayList<>();
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
                ", ticketTypes=" + ticketTypes.size() +
                '}';
    }
}
