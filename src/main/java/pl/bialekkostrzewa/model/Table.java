package pl.bialekkostrzewa.model;

public class Table extends Resource {

    private int number;
    private int numOfPeople;

    public Table(){

    }

    public Table(String id, double price, int number, int numOfPeople) {
        super(id, price);
        this.number = number;
        this.numOfPeople = numOfPeople;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setNumOfPeople(int numOfPeople) {
        this.numOfPeople = numOfPeople;
    }

    public int getNumber() {
        return number;
    }

    public int getNumOfPeople() {
        return numOfPeople;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Table{");
        sb.append(super.toString());
        sb.append(" number=").append(number);
        sb.append(", numOfPeople=").append(numOfPeople);
        sb.append('}');
        return sb.toString();
    }
}
