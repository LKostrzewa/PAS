package pl.bialekkostrzewa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.bialekkostrzewa.model.Client;
import pl.bialekkostrzewa.model.Reservation;
import pl.bialekkostrzewa.model.Resource;
import pl.bialekkostrzewa.service.ReservationService;
import pl.bialekkostrzewa.service.ResourceService;
import pl.bialekkostrzewa.service.UserService;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/reservations")
public class ReservationController {

    private ReservationService reservationService;
    private UserService userService;
    private ResourceService resourceService;

    @Autowired
    public ReservationController(ReservationService reservationService, UserService userService, ResourceService resourceService) {
        this.reservationService = reservationService;
        this.userService = userService;
        this.resourceService = resourceService;
    }

    @GetMapping("/add-reservation")
    public ModelAndView showReservationForm(){
        ModelAndView modelAndView = new ModelAndView("reservationForm", "reservation", new Reservation());
        modelAndView.addObject("clients", userService.getAllClients());
        modelAndView.addObject("resources", resourceService.getAllResources());
        return modelAndView;
    }

    @PostMapping("/add-reservation")
    public String addReservation(@Valid @ModelAttribute Reservation reservation){
        reservation.setClient((Client)userService.getUser(reservation.getClient().getLogin()));
        reservation.setResource(resourceService.getResource(reservation.getResource().getId()));
        reservationService.startReservation(reservation);
        return "reservationForm";
    }

    @RequestMapping
    public ModelAndView showAllReservations(){
        return new ModelAndView("allReservation", "reservation", reservationService.getAllReservations());
    }

    @RequestMapping("/delete-reservation/{id}")
    public String deleteResource(@PathVariable String id){
        reservationService.deleteReservation(id);
        return "redirect:/reservations/";
    }

    /*@RequestMapping("/update-reservation/{id}")
    public ModelAndView showUpdateForm(@PathVariable String id){
        Reservation reservation = reservationService.getReservation(id);
        return new ModelAndView("reservationUpdateForm", "reservation", reservation);
    }*/

}
