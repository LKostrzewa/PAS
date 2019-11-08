package service;

import model.Resource;
import repository.ResourceRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ResourceService {

    private ResourceRepository resources = new ResourceRepository();

    public void createShowing(String id, String name, int ageRestriction, int room, int hour, int freeTickets, double price) throws Exception {

    }

    public void endResource(String id){
        resources.delete(id);
    }

    public List<Resource> getAllResources(){
        return resources.getAll();
    }

    public Resource getResource(String id) {
        return resources.get(id);
    }
}
