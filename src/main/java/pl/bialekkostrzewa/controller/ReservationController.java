package pl.bialekkostrzewa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.bialekkostrzewa.service.ReservationService;

@Controller
@RequestMapping("/reservations")
public class ReservationController {

    private ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    //TODO tutaj luzna sugestia pewnie widok sie zmieni dla upozadkowania
    @RequestMapping("/all-reservations")
    public ModelAndView showAllReservations(){
        return new ModelAndView("allResource", "resource", reservationService.getAllReservations());
    }


}
