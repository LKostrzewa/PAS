package pl.bialekkostrzewa.service;

import pl.bialekkostrzewa.model.Client;
import pl.bialekkostrzewa.model.NormalClient;
import pl.bialekkostrzewa.model.RegularClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.bialekkostrzewa.repository.UserRepository;

class UserServiceTest {
    @Test
    void ClientTypeTest(){
        UserService us = new UserService(new UserRepository());

        us.addUser(new Client("pawel98", "Pawel", "Bialek", new NormalClient()));

        us.changeClientsType("pawel98", new RegularClient());

        Assertions.assertTrue(((Client)us.getUser("pawel98")).getType() instanceof RegularClient);
    }
}