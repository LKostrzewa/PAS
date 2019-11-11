package pl.bialekkostrzewa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.bialekkostrzewa.service.ReservationService;

@Controller
public class ReservationController {

    private ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    /*@GetMapping
    public String RezerwacjaMajster(){
        reservationService.
    }*/
}
