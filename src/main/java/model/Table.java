package model;

public class Table extends Resource {

    private int number;
    private int numOfPeople;

    public Table(String id, double price, int number, int numOfPeople) {
        super(id, price);
        this.number = number;
        this.numOfPeople = numOfPeople;
    }

    public int getNumber() {
        return number;
    }

    public int getNumOfPeople() {
        return numOfPeople;
    }
}
