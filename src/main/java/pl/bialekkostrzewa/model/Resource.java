package pl.bialekkostrzewa.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;

public class Resource {

    @NotBlank(message = "Empty ID given")
    private String id;
    @Min(value = 0, message = "Price has to be positive decimal")
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
