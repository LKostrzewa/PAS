package pl.bialekkostrzewa.model;

import javax.validation.constraints.Min;

public class BallRoom extends Resource {

    private String description;
    @Min(0)
    private int numOfRooms;

    public BallRoom(){

    }

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

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNumOfRooms(int numOfRooms) {
        this.numOfRooms = numOfRooms;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BallRoom ");
        sb.append(super.toString());
        sb.append(", ").append(description);
        sb.append(", has ").append(numOfRooms);
        sb.append(" rooms");
        return sb.toString();
    }
}
