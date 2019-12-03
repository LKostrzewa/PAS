package pl.bialekkostrzewa.model;

import java.time.LocalDateTime;

public class Reservation {

    private String id;
    private Resource resource;
    private Client client;
    private LocalDateTime beginning;
    private LocalDateTime ending;

    public Reservation(){
    }

    public Reservation(String id, Resource resource, Client client, LocalDateTime beginning) {
        this.id = id;
        this.resource = resource;
        this.client = client;
        this.beginning = beginning;
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

    public LocalDateTime getBeginning() {
        return beginning;
    }

    public void setEnding(LocalDateTime ending) {
        this.ending = ending;
    }

    public LocalDateTime getEnding() {
        return ending;
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
