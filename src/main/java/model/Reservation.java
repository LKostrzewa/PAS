package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Reservation {

    private String id;
    private Showing showing;
    //TODO czy tutaj mapa cz lista hmmmm ?
    private List<Client> clients;

    public Reservation(String id, Showing showing, List<Client> clients) {
        this.id = id;
        this.showing = showing;
        this.clients = clients;
    }

    public String getId() {
        return id;
    }

    public Showing getShowing() {
        return showing;
    }

    public List<Client> getClients() {
        return Collections.unmodifiableList(clients);
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id='" + id + '\'' +
                ", showing=" + showing +
                ", clients=" + clients +
                '}';
    }
}
