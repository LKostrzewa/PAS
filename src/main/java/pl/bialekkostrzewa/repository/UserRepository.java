package pl.bialekkostrzewa.repository;

import org.springframework.stereotype.Repository;
import pl.bialekkostrzewa.model.Client;
import pl.bialekkostrzewa.model.User;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository extends RepositoryTemplate<User> {

    public List<Client> getAllClients(){
        List<Client> clients = new ArrayList<>();
        for(User c : getAll()){
            if(c instanceof Client) clients.add((Client) c);
        }
        return clients;
    }
}
