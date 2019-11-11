package pl.bialekkostrzewa.model;

public class BallRoom extends Resource {

    private String description;
    private int numOfRooms;

    public BallRoom(String id, double price, String description, int numOfRooms) {
        super(id, price);
        this.description = description;
        this.numOfRooms = numOfRooms;
    }

    public String getDescription() {
        return description;
    }

    public int getNumOfRooms() {
        return numOfRooms;
    }
}
