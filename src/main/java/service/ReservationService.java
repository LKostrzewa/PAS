package service;

import model.*;
import repository.ReservationRepository;

import java.util.List;

public class ReservationService {

    private ReservationRepository reservations = new ReservationRepository();

    public void reserveResource(String id, Resource resource, Client client) throws Exception{
        //TODO Kooncepcja taka nwm co do tych czasow i moze jakas logika bedzie tez nie znaju
        for(Reservation r : reservations.getAll()){
            if(r.getResource().getId().equals(resource.getId())){
                throw new Exception("stolik zarezerwowany ! ");
            }
        }
        reservations.add(id, new Reservation(id, resource, client));
    }

    //tutaj mialem taka wstepna koncepcje ale to w sumie jeden chuj i sam nwm

    /*public void reserveTable(String id, Table table, Client client){

    }

    public void reserveBallRoom(String id, BallRoom ballRoom, Client client){

    }*/

    public void cancelReservation(String id){
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
