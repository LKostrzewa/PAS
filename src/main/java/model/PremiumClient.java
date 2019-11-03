package model;

public class PremiumClient extends ClientType {

    public PremiumClient() {
        setPlacesLimit(20);
    }

    @Override
    public double countDiscount(double base) {
        return 0.3 * base;
    }
}
