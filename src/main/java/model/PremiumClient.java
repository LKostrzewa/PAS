package model;

public class PremiumClient implements ClientType {
    @Override
    public double countDiscount(double base) {
        return 0.3 * base;
    }

    @Override
    public String toString() {
        return "premium ";
    }
}
