package model;

public class Showing {

    private String id;
    private String name;
    private int ageRestriction;
    private int room;
    //TODO tu mozna dac jakiegos datetime jak to robilem teraz dopiero pomyslalem i juz nie chce mi sie zmieniac xd
    private int hour;
    private int freeTickets;
    private double price;

    public Showing(String id, String name, int ageRestriction, int room, int hour, int freeTickets, double price) {
        this.id = id;
        this.name = name;
        this.ageRestriction = ageRestriction;
        this.room = room;
        this.hour = hour;
        this.freeTickets = freeTickets;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAgeRestriction() {
        return ageRestriction;
    }

    public int getRoom() {
        return room;
    }

    public int getHour() {
        return hour;
    }

    public int getFreeTickets() {
        return freeTickets;
    }

    public double getPrice() {
        return price;
    }

    public void setFreeTickets(int freeTickets) {
        this.freeTickets = freeTickets;
    }

    @Override
    public String toString() {
        return "Showing{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", ageRestriction=" + ageRestriction +
                ", room=" + room +
                ", hour=" + hour +
                ", freeTickets=" + freeTickets +
                ", price=" + price +
                '}';
    }
}
