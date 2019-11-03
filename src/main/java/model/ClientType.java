package model;

public abstract class ClientType {
    private int placesLimit;

    public int getPlacesLimit() {
        return placesLimit;
    }

    public void setPlacesLimit(int placesLimit) {
        this.placesLimit = placesLimit;
    }

    @Override
    public String toString() {
        return "ClientType{" +
                "placesLimit=" + placesLimit +
                '}';
    }

    public abstract double countDiscount(double base);
}
