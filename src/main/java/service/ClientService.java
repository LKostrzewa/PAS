package service;

import model.Client;
import model.ClientType;
import model.NormalClient;
import repository.ClientRepository;

import java.util.List;

public class ClientService {

    private ClientRepository clients = new ClientRepository();

    //TODO czy id nie generowac wewnatrz funkcji hmmmm ?
    public void createClient(String id, String name, String surname, int age){
        clients.add(id, new Client(id, name, surname, age, new NormalClient()));
    }

    public void changeClientsType(String id, ClientType type){
        clients.get(id).setType(type);
    }

    public void deleteClient(String id){
        clients.delete(id);
    }

    public List<Client> getAllClients(){
        return clients.getAll();
    }
}
