package service;

import model.BallRoom;
import model.Resource;
import model.Table;
import repository.ResourceRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ResourceService {

    private ResourceRepository resources = new ResourceRepository();

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
