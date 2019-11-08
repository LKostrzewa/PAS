package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Reservation {

    private String id;
    private Resource resource;
    private Client client;
    private LocalDateTime beginning;
    private LocalDateTime ending;

    public Reservation(String id, Resource resource, Client client) {
        this.id = id;
        this.resource = resource;
        this.client = client;
    }

    public String getId() {
        return id;
    }

    public Resource getResource() {
        return resource;
    }

    public Client getClient() {
        return client;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Reservation{");
        sb.append("id='").append(id).append('\'');
        sb.append(", resource=").append(resource);
        sb.append(", client=").append(client);
        sb.append(", beginning=").append(beginning);
        sb.append(", ending=").append(ending);
        sb.append('}');
        return sb.toString();
    }
}
