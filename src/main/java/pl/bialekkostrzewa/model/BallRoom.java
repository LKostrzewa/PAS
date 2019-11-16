package pl.bialekkostrzewa.model;

public class BallRoom extends Resource {

    private String description;
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
        final StringBuilder sb = new StringBuilder("BallRoom{");
        sb.append(super.toString());
        sb.append(" description='").append(description).append('\'');
        sb.append(", numOfRooms=").append(numOfRooms);
        sb.append('}');
        return sb.toString();
    }
}
