package pl.bialekkostrzewa.model;

public class RegularClient implements ClientType{
    @Override
    public double countDiscount(double base) {
        return (base >= 50) ? base * 0.25 : base * 0.2;
    }

    @Override
    public String toString() {
        return "Regular ";
    }
}
