package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Client {

    //TODO do wszystkich rzeczy z model czy id wgl trzymac jako atrybut klasy ???
    private String  id;
    private String name;
    private String surname;
    private int age;
    private ClientType type;
    //TODO analogicznie co do rezerwacji
    private List<Reservation> reservations = new ArrayList<Reservation>();

    public Client(String id, String name, String surname, int age, ClientType type) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", type=" + type +
                '}';
    }

    public int getAge() {
        return age;
    }

    public List<Reservation> getReservations() {
        return Collections.unmodifiableList(reservations);
    }

    public void setType(ClientType type) {
        this.type = type;
    }

    public int getLimit(){
        return type.getPlacesLimit();
    }

    public double getDiscount(double base){
        return type.countDiscount(base);
    }

    public void addReservation(Reservation reservation){
        reservations.add(reservation);
    }
}
