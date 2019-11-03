package service;

import model.Client;
import model.Reservation;
import model.Showing;
import repository.ReservationRepository;

import java.util.Comparator;
import java.util.List;

public class ReservationService {

    private ReservationRepository reservations = new ReservationRepository();

    public void reserveTicket(String id, Showing showing, List<Client> clients) throws Exception{
        int min = clients.stream().min(Comparator.comparing(Client::getAge)).get().getAge();
        if(showing.getFreeTickets() >= clients.size() && clients.get(0).getLimit() >= clients.size() && min >= showing.getAgeRestriction()){
            reservations.add(id, new Reservation(id, showing, clients));
        }
        else throw new Exception("no nie da rady"); //TODO dodac wyajtki wlasne
    }

    public void cancelTicket(String id){
        Reservation r = reservations.get(id);
        int tickNum = r.getShowing().getFreeTickets();
        r.getShowing().setFreeTickets(tickNum + r.getClients().size());
        reservations.delete(id);
    }

    public double countTicketPrice(String id){
        Reservation reservation = reservations.get(id);
        double price = 0;
        for (Client c : reservation.getClients()) {
            price += reservation.getShowing().getPrice();
            if(c.getAge() <= 18 || c.getAge() >= 65) price-=3;
        }
        return price - reservation.getClients().get(0).getDiscount(price);
    }

    public List<Reservation> getAllReservations(){
        return reservations.getAll();
    }

    public Reservation getReservation(String id){
        return reservations.get(id);
    }
}
