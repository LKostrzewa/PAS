package pl.bialekkostrzewa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bialekkostrzewa.model.BallRoom;
import pl.bialekkostrzewa.model.Resource;
import pl.bialekkostrzewa.model.Table;
import pl.bialekkostrzewa.repository.ResourceRepository;

import java.util.List;

@Service
public class ResourceService {

    private ResourceRepository resources = new ResourceRepository();

    @Autowired
    public ResourceService(ResourceRepository resources) {
        this.resources = resources;
    }

    public void createBallRoom(String id, double price, String description, int numOfRooms) {
        resources.add(id, new BallRoom(id, price, description, numOfRooms));
    }

    public void createTable(String id, double price, int number, int numOfPeople){
        resources.add(id, new Table(id, price, number, numOfPeople));
    }

    public void deleteResource(String id){
        resources.delete(id);
    }

    public List<Resource> getAllResources(){
        return resources.getAll();
    }

    public Resource getResource(String id) {
        return resources.get(id);
    }
}
