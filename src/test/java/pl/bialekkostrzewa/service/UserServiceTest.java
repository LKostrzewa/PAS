package pl.bialekkostrzewa.service;

import pl.bialekkostrzewa.model.RegularClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserServiceTest {
    @Test
    void ClientTypeTest(){
        UserService us = new UserService();

        us.createClient("siusiak", "Pawel", "Bialek");

        us.changeClientsType("siusiak", new RegularClient());

        Assertions.assertTrue(us.getClient("siusiak").getType() instanceof RegularClient);
    }
}