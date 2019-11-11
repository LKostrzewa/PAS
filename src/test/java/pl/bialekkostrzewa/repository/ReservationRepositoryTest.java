package pl.bialekkostrzewa.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.bialekkostrzewa.model.*;

import static org.junit.jupiter.api.Assertions.*;

class ReservationRepositoryTest {

    @Test
    void getReservationsForClientTest() {

        ReservationRepository rr = new ReservationRepository();

        Client c1 = new Client("pb98", "Pawel", "Bialek", new NormalClient());
        Client c2 = new Client("lk98", "Lukasz", "Kostrzewa", new NormalClient());

        Table t1 = new Table("1", 45, 1, 2);
        Table t2 = new Table("2", 65, 2, 3);
        Table t3 = new Table("3", 105, 3, 4);
        BallRoom b = new BallRoom("4", 3500, "bardzo fajna sala balowa", 4);

        rr.add("1", new Reservation("1", t1, c1));
        rr.add("2", new Reservation("2", t3, c1));

        Assertions.assertEquals(rr.getReservationsForClient("pb98").size(), 2);

        rr.add("3", new Reservation("3", t2, c2));
        rr.add("4", new Reservation("4", t1, c2));
        rr.add("5", new Reservation("5", b, c2));

        Assertions.assertEquals(rr.getReservationsForClient("lk98").size(), 3);

    }

    @Test
    void getReservationsForResourceTest() {

        ReservationRepository rr = new ReservationRepository();

        Client c1 = new Client("pb98", "Pawel", "Bialek", new NormalClient());
        Client c2 = new Client("lk98", "Lukasz", "Kostrzewa", new NormalClient());
        Client c3 = new Client("am77", "Adam", "Malysz", new PremiumClient());

        Table t1 = new Table("1", 45, 1, 2);
        BallRoom b = new BallRoom("4", 3500, "bardzo fajna sala balowa", 4);

        rr.add("1", new Reservation("1", t1, c1));
        rr.add("2", new Reservation("2", t1, c2));
        rr.add("3", new Reservation("3", t1, c3));

        Assertions.assertEquals(rr.getReservationsForResource("1").size(), 3);

        rr.add("4", new Reservation("4", b, c3));

        Assertions.assertEquals(rr.getReservationsForResource("4").size(),1);
    }
}