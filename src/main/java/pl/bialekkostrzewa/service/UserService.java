package pl.bialekkostrzewa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bialekkostrzewa.model.*;
import pl.bialekkostrzewa.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {


    private UserRepository users;

    @Autowired
    public UserService(UserRepository users) {
        this.users = users;
    }

    public void addUser(User user){
        users.add(user.getUsername(), user);
    }

    public void changeClientsType(String login, ClientType type) throws RuntimeException{
        User user = users.get(login);
        if(user instanceof Client){
            Client client = (Client)user;
            client.setType(type);
        }
        else throw new RuntimeException("This user is not a client");
    }

    public User getUser(String login){
        return users.get(login);
    }

    public void updateUser(String id, User user){
        users.update(id, user);
    }

    public void activateUser(String login){
        users.get(login).setEnabled(true);
    }

    public void deactivateUser(String login){
        users.get(login).setEnabled(false);
    }

    public List<User> getAllUsers(){
        return users.getAll();
    }

    public List<Client> getAllClients(){
        return users.getAllClients();
    }

    public List<Client> getAllActiveClients(){
        return users.getAllActiveClients();
    }

    /*//TODO
    //nie dziala logowanie wiec moznaby przyjac ze tutaj to nie dziala :/
    public void addUserToPool(User user, String role){
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //addUser(user);
    }

    @Transactional
    public void addUserToPool2(MyUserDetails user, String role){
        Client client = new Client();
        client.setUsername(user.getUsername());
        client.setPassword(user.getPassword());
        client.setName(user.getName());
        client.setSurname(user.getSurname());
        client.setEnabled(user.isEnabled());
        client.setType(new NormalClient());
        addUser(client);
        /*List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        addUser(client);
    }*/
}
