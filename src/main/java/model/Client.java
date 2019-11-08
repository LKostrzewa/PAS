package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Client extends User{


    private ClientType type;
    //TODO narazie wypierdolka jak chcesz to zostaw ale imo to tylko generuje dodatkowe problemy
    //private List<Reservation> reservations = new ArrayList<Reservation>();

    public Client(String login, String name, String surname, ClientType type) {
        super(login, name, surname);
        this.type = type;
    }

    @Override
    public String toString() {
        return "Client{" +
                "login" + getLogin() + '\'' +
                ", name='" + getName() + '\'' +
                ", surname='" + getSurname() + '\'' +
                ", type=" + type +
                '}';
    }

    /*public List<Reservation> getReservations() {
        return Collections.unmodifiableList(reservations);
    }*/

    public ClientType getType() {
        return type;
    }

    public void setType(ClientType type) {
        this.type = type;
    }

    public double getDiscount(double base){
        return type.countDiscount(base);
    }

    /*public void addReservation(Reservation reservation){
        reservations.add(reservation);
    }*/
}
