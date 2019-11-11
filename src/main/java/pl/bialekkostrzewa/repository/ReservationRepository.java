package pl.bialekkostrzewa.repository;

import org.springframework.stereotype.Repository;
import pl.bialekkostrzewa.model.Reservation;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ReservationRepository extends RepositoryTemplate<Reservation> {

    List<Reservation> getReservationsForClient(String login){
        return getAll().stream().filter(
                r -> r.getClient().getLogin().equals(login))
                .collect(Collectors.toList());
    }

    List<Reservation> getReservationsForResource(String id){
        return getAll().stream().filter(
                r -> r.getResource().getId().equals(id))
                .collect(Collectors.toList());
    }
}
