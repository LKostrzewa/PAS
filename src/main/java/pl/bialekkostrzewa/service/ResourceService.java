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

    private ResourceRepository resources;

    @Autowired
    public ResourceService(ResourceRepository resources) {
        this.resources = resources;
    }

    public boolean addResource(Resource resource) {
        if (resources.getData().containsKey(resource.getId())) return false;
        else {
            resources.add(resource.getId(), resource);
            return true;
        }
    }

    public void deleteResource(String id) {
        resources.delete(id);
    }

    public List<Resource> getAllResources() {
        return resources.getAll();
    }

    public Resource getResource(String id) {
        return resources.get(id);
    }

    public List<Table> getAllTables() {
        return resources.getAllTables();
    }

    public List<BallRoom> getAllBallRoom() {
        return resources.getAllBallRooms();
    }

    public void updateResource(String id, Resource resource) {
        resources.update(id, resource);
    }
}
