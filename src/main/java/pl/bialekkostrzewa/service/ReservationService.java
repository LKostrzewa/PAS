package pl.bialekkostrzewa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bialekkostrzewa.exceptions.InactiveClientException;
import pl.bialekkostrzewa.exceptions.ResourceTakenException;
import pl.bialekkostrzewa.model.Client;
import pl.bialekkostrzewa.model.Reservation;
import pl.bialekkostrzewa.model.Resource;
import pl.bialekkostrzewa.repository.ReservationRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationService {


    private ReservationRepository reservations;

    @Autowired
    public ReservationService(ReservationRepository reservations) {
        this.reservations = reservations;
    }

    public void startReservation(Reservation reservation) /*Runtime bo w testach wygoniej :)*/throws RuntimeException {
        if(reservations.getResevedReservations(reservation.getResource().getId()).isPresent())
            throw new ResourceTakenException("Reserwacja niemozliwa zasob jest zajety");
        if(!reservation.getClient().isActive()){
            throw new InactiveClientException("Klient jest nieaktywny");
        }
        else reservations.add(reservation.getId(), reservation);
    }

    public void endReservation(String id, LocalDateTime end){
        Reservation r = getReservation(id);
        if(r.getClient().isActive())
            r.setEnding(end);
    }

    public void deleteReservation(String id){
        if(reservations.get(id).getEnding() != null)
            reservations.delete(id);
    }

    public double countReservationPrice(String id){
        Reservation r = reservations.get(id);
        double price = r.getResource().getPrice();
        return price - r.getClient().getDiscount(price);
    }

    public List<Reservation> getAllReservations(){
        return reservations.getAll();
    }

    public Reservation getReservation(String id){
        return reservations.get(id);
    }
}
