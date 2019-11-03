package model;

public class NormalClient extends ClientType {

    public NormalClient() {
        setPlacesLimit(2);
    }

    @Override
    public double countDiscount(double base) {
        return 0;
    }
}
