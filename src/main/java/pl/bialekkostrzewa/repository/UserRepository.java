package pl.bialekkostrzewa.repository;

import org.springframework.stereotype.Repository;
import pl.bialekkostrzewa.model.Administrator;
import pl.bialekkostrzewa.model.Client;
import pl.bialekkostrzewa.model.Manager;
import pl.bialekkostrzewa.model.User;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository extends RepositoryTemplate<User> {

    //proba zeby miec obiekty admina i managera dziala na tyle ze nie mozna stworzyc drugiego konta o loginie admin ale myslalem
    //ze wystarczy tylko tu dodac i bd git XD ale to niestety nie takie proste i i tak musi byc w configu to
    public UserRepository(){
        add("admin", new Administrator("admin", "password", "Jan", "Kowalski"));
        add("manger", new Manager("manager", "password", "Piotr", "Nowak"));
    }

    public List<Client> getAllClients(){
        List<Client> clients = new ArrayList<>();
        for(User c : getAll()){
            if(c instanceof Client) clients.add((Client) c);
        }
        return clients;
    }

    public List<Client> getAllActiveClients(){
        List<Client> clients = new ArrayList<>();
        for(User c : getAll()){
            if(c instanceof Client && c.isActive()) clients.add((Client) c);
        }
        return clients;
    }
}
