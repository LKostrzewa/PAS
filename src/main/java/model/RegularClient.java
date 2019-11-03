package model;

public class RegularClient extends ClientType{

    public RegularClient() {
        setPlacesLimit(6);
    }

    @Override
    public double countDiscount(double base) {
        return (base >= 50) ? base * 0.25 : base * 0.2;
    }
}
