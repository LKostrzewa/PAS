package pl.bialekkostrzewa.model;

public enum ClientType {
    NORMAL("Normal"), REGULAR("Regular"), PREMIUM("Premium");

    private String name;

    ClientType(String name){
        this.name = name;
    }

    public double countDiscount(double base){
        switch (this){
            case NORMAL:
                return 0;
            case REGULAR:
                return (base >= 50) ? base * 0.25 : base * 0.2;
            case PREMIUM:
                return 0.3 * base;
        }
        return 0;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
