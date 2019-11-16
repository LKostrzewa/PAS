package pl.bialekkostrzewa.repository;

import org.springframework.stereotype.Repository;
import pl.bialekkostrzewa.model.BallRoom;
import pl.bialekkostrzewa.model.Resource;
import pl.bialekkostrzewa.model.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ResourceRepository extends RepositoryTemplate<Resource> {

    public List<Table> getAllTables(){
        List<Table> list = new ArrayList<>();
        getAll().stream().filter(t -> t instanceof Table)
                .forEach(t -> list.add((Table) t));
        return list;
    }

    public List<BallRoom> getAllBallRooms(){
        List<BallRoom> list = new ArrayList<>();
        getAll().stream().filter(b -> b instanceof BallRoom)
                .forEach(b -> list.add((BallRoom) b));
        return list;
    }
}
