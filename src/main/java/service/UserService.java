package service;

import model.*;
import repository.UserRepository;

import java.util.List;

public class UserService {

    private UserRepository users = new UserRepository();

    public void createClient(String login, String name, String surname){
        users.add(login, new Client(login, name, surname, new NormalClient()));
    }

    public void createAdministrator(String login, String name, String surname){
        users.add(login, new Administrator(login, name, surname));
    }

    public void createManager(String login, String name, String surname){
        users.add(login, new Manager(login, name, surname));
    }

    public void changeClientsType(String login, ClientType type){
        User user = users.get(login);
        if(user instanceof Client){
            Client client = (Client)user;
            client.setType(type);
        }
        //else throw Exception("PANIE CO PAN "); //TODO nasze wyjatki albo i nie bo w sumie to moze jakos bd z przegladarki tak jak karbo czary mary odpierdalał
    }

    public User getUser(String login){
        return users.get(login);
    }

    public Client getClient(String login){
        return (Client)users.get(login);
    }

    public void deleteUser(String id){
        users.delete(id);
    }

    public List<User> getAllUsers(){
        return users.getAll();
    }

    public List<Client> getAllClients(){
        return users.getAllClients();
    }
}
