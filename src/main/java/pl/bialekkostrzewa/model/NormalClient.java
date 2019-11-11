package pl.bialekkostrzewa.model;

public class NormalClient implements ClientType {
    @Override
    public double countDiscount(double base) {
        return 0;
    }

    @Override
    public String toString() {
        return "normal ";
    }

}
