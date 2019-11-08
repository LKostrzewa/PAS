package service;

import model.RegularClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    @Test
    public void firsTest(){
        UserService us = new UserService();

        us.createClient("siusiak", "Pawel", "Bialek");

        us.changeClientsType("siusiak", new RegularClient());

        Assertions.assertTrue(us.getClient("siusiak").getType() instanceof RegularClient);
    }
}