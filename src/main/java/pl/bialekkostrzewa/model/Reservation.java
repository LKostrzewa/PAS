package pl.bialekkostrzewa.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class Reservation {

    private String id;
    private Resource resource;
    private Client client;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") private LocalDateTime beginning;
    //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime ending;

    public Reservation(){
        beginning = LocalDateTime.now();
        ending = LocalDateTime.now().plusHours(10);
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

    public void setId(String id) {
        this.id = id;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setBeginning(LocalDateTime beginning) {
        this.beginning = beginning;
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
