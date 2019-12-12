package pl.bialekkostrzewa.model;

import javax.validation.constraints.Min;

public class Table extends Resource {

    @Min(0)
    private int number;
    @Min(0)
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
        final StringBuilder sb = new StringBuilder("Table ");
        sb.append(super.toString());
        sb.append(" number ").append(number);
        sb.append(", can hold ").append(numOfPeople);
        sb.append(" people");
        return sb.toString();
    }
}
