package pl.bialekkostrzewa.repository;

import org.springframework.stereotype.Repository;
import pl.bialekkostrzewa.model.Reservation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ReservationRepository extends RepositoryTemplate<Reservation> {

    public List<Reservation> getReservationsForClient(String login){
        return getAll().stream().filter(
                r -> r.getClient().getLogin().equals(login))
                .collect(Collectors.toList());
    }

    public List<Reservation> getReservationsForResource(String id){
        return getAll().stream().filter(
                r -> r.getResource().getId().equals(id))
                .collect(Collectors.toList());
    }

    public Optional<Reservation> getReservedReservations(String id){
        return getReservationsForResource(id).stream().filter(
                r -> r.getEnding() == null).findAny();
    }
}
