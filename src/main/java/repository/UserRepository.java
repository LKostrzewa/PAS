package repository;

import model.Client;
import model.Resource;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository extends Repository<User>{

    public List<Client> getAllClients(){
        List<Client> clients = new ArrayList<>();
        for(User c : getAll()){
            if(c instanceof Client) clients.add((Client) c);
        }
        return clients;
    }

    public void activateUser(String key){
        get(key).setActive(true);
    }

    public void deactivateUser(String key){
        get(key).setActive(false);
    }
}
