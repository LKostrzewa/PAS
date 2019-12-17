package pl.bialekkostrzewa.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.bialekkostrzewa.model.*;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ReservationRepositoryTest {

    @Test
    void getReservationsForClientTest() {

        ReservationRepository rr = new ReservationRepository();

        Client c1 = new Client("pb98", "Pawel", "Bialek", ClientType.NORMAL);
        Client c2 = new Client("lk98", "Lukasz", "Kostrzewa", ClientType.NORMAL);

        Table t1 = new Table("1", 45, 1, 2);
        Table t2 = new Table("2", 65, 2, 3);
        Table t3 = new Table("3", 105, 3, 4);
        BallRoom b = new BallRoom("4", 3500, "bardzo fajna sala balowa", 4);

        LocalDateTime ld1= LocalDateTime.of(2019, 10, 12,12,30);
        LocalDateTime ld2 = ld1.plusHours(2);

        rr.add("1", new Reservation("1", t1, c1, ld1));
        rr.add("2", new Reservation("2", t3, c1, ld2));

        Assertions.assertEquals(rr.getReservationsForClient("pb98").size(), 2);

        rr.add("3", new Reservation("3", t2, c2, ld2));
        rr.add("4", new Reservation("4", t1, c2, ld1));
        rr.add("5", new Reservation("5", b, c2, ld1));

        Assertions.assertEquals(rr.getReservationsForClient("lk98").size(), 3);

    }

    @Test
    void getReservationsForResourceTest() {

        ReservationRepository rr = new ReservationRepository();

        Client c1 = new Client("pb98", "Pawel", "Bialek", ClientType.NORMAL);
        Client c2 = new Client("lk98", "Lukasz", "Kostrzewa", ClientType.NORMAL);
        Client c3 = new Client("am77", "Adam", "Malysz", ClientType.PREMIUM);

        Table t1 = new Table("1", 45, 1, 2);
        BallRoom b = new BallRoom("4", 3500, "bardzo fajna sala balowa", 4);

        LocalDateTime ld1= LocalDateTime.of(2019, 10, 12,12,30);
        LocalDateTime ld2 = ld1.plusHours(2);

        rr.add("1", new Reservation("1", t1, c1, ld1));
        rr.add("2", new Reservation("2", t1, c2, ld2));
        rr.add("3", new Reservation("3", t1, c3, ld1));

        Assertions.assertEquals(rr.getReservationsForResource("1").size(), 3);

        rr.add("4", new Reservation("4", b, c3, ld1));

        Assertions.assertEquals(rr.getReservationsForResource("4").size(),1);
    }
}