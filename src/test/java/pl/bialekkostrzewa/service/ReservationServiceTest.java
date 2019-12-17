package pl.bialekkostrzewa.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.bialekkostrzewa.model.*;
import pl.bialekkostrzewa.repository.ReservationRepository;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ReservationServiceTest {

    @Test
    void startReservationTest() {
        ReservationService rs = new ReservationService(new ReservationRepository());

        Client client = new Client("jk98", "jan", "kowalski", ClientType.NORMAL);
        Table table = new Table("1", 13.20, 1, 2);
        Table table1 = new Table("2", 20, 2, 4);

        rs.startReservation(new Reservation("1", table, client, LocalDateTime.now()));

        Assertions.assertEquals(rs.getAllReservations().size(), 1);

        rs.startReservation(new Reservation("2", table1, client, LocalDateTime.now().plusHours(4)));

        Assertions.assertEquals(rs.getAllReservations().size(), 2);

        Assertions.assertThrows(RuntimeException.class, () -> rs.startReservation(new Reservation("3", table1, client, LocalDateTime.now())));

        Assertions.assertEquals(rs.getAllReservations().size(), 2);
    }

    @Test
    void deleteReservation() {
        ReservationService rs = new ReservationService(new ReservationRepository());

        Client client = new Client("jk98", "jan", "kowalski", ClientType.NORMAL);
        Table table = new Table("1", 13.20, 1, 2);
        Table table1 = new Table("2", 20, 2, 4);

        rs.startReservation(new Reservation("1", table, client, LocalDateTime.now()));
        rs.startReservation(new Reservation("2", table1, client, LocalDateTime.now().plusHours(4)));

        rs.deleteReservation("1");

        Assertions.assertEquals(rs.getAllReservations().size(), 1);

        rs.endReservation("2", LocalDateTime.now().plusDays(2));
        rs.deleteReservation("2");

        Assertions.assertEquals(rs.getAllReservations().size(), 1);
    }
}