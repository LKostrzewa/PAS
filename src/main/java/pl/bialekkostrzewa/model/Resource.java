package pl.bialekkostrzewa.model;

public abstract class Resource {

    private String id;
    private double price;

    public Resource(String id, double price) {
        this.id = id;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }
}
