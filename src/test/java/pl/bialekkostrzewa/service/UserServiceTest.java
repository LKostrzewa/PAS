package pl.bialekkostrzewa.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.bialekkostrzewa.model.Client;
import pl.bialekkostrzewa.model.ClientType;
import pl.bialekkostrzewa.repository.UserRepository;

class UserServiceTest {
    @Test
    void ClientTypeTest(){
        UserService us = new UserService(new UserRepository());

        us.addUser(new Client("pawel98", "Pawel", "Bialek", ClientType.NORMAL));

        Client c1 = (Client)us.getUser("pawel98");

        Assertions.assertEquals(c1.getDiscount(10), 0);

        us.addUser(new Client("lukko98", "Lukasz", "Kostrzewa", ClientType.PREMIUM));

        Client c2 = (Client)us.getUser("lukko98");

        Assertions.assertEquals(c2.getDiscount(10), 3);

        //System.out.println(c2.getType().name());

        //us.changeClientsType("pawel98", new RegularClient());

        //Assertions.assertTrue();
    }
}