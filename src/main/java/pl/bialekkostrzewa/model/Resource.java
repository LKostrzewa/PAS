package pl.bialekkostrzewa.model;

import javax.validation.constraints.Min;

public class Resource {

    private String id;
    @Min(0)
    private double price;

    public Resource(){

    }

    public Resource(String id, double price) {
        this.id = id;
        this.price = price;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("id ").append(id);
        sb.append(", costs ").append(price);
        return sb.toString();
    }
}
