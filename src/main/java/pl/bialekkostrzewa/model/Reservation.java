package pl.bialekkostrzewa.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class Reservation {

    @NotBlank(message = "id cannot be blank")
    private String id;
    private Resource resource;
    private Client client;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") private LocalDateTime beginning;
    //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime ending;

    public Reservation(){
        beginning = LocalDateTime.now();
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
        final StringBuilder sb = new StringBuilder("Reservation ");
        sb.append("id ").append(id).append(" of");
        sb.append(" resource ").append(resource.getId()).append(" by");
        sb.append(" client ").append(client.getLogin());
        sb.append(", begins ").append(beginning);
        sb.append(", ends ").append(ending);
        return sb.toString();
    }
}
