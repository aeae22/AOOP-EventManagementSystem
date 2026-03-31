package model;

public class TicketType {
    private String name;
    private double priceMultiplier;

    public TicketType(String name, double priceMultiplier) {
        this.name = name;
        this.priceMultiplier = priceMultiplier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPriceMultiplier() {
        return priceMultiplier;
    }

    public void setPriceMultiplier(double priceMultiplier) {
        this.priceMultiplier = priceMultiplier;
    }

    public double calculatePrice(double basePrice) {
        return basePrice * priceMultiplier;
    }

    @Override
    public String toString() {
        return "model.TicketType{" +
                "name='" + name + '\'' +
                ", priceMultiplier=" + priceMultiplier +
                '}';
    }
}
