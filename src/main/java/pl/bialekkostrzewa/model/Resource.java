package pl.bialekkostrzewa.model;

public abstract class Resource {

    private String id;
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
        sb.append("id='").append(id).append('\'');
        sb.append(", price=").append(price);
        return sb.toString();
    }
}
