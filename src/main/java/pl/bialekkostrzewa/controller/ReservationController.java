package pl.bialekkostrzewa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.bialekkostrzewa.model.Client;
import pl.bialekkostrzewa.model.Reservation;
import pl.bialekkostrzewa.service.ReservationService;
import pl.bialekkostrzewa.service.ResourceService;
import pl.bialekkostrzewa.service.UserService;

import javax.validation.Valid;
import java.time.LocalDateTime;

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
        modelAndView.addObject("clients", userService.getAllActiveClients());
        modelAndView.addObject("resources", resourceService.getAllResources());
        return modelAndView;
    }

    @PostMapping("/add-reservation")
    public ModelAndView addReservation(@Valid @ModelAttribute Reservation reservation, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            //model.addAttribute("clients", userService.getAllActiveClients());
            return showReservationForm();
            //return "reservationForm";
        }
        try{
            reservation.setClient((Client)userService.getUser(reservation.getClient().getLogin()));
            reservation.setResource(resourceService.getResource(reservation.getResource().getId()));
            reservationService.startReservation(reservation);
        }
        catch (NullPointerException e){
            return new ModelAndView("exception", "msg", "requested object is not accessible right now");
            //model.addAttribute("msg", "requested object is not accessible right now");
            //return "exception";
        }
        catch (RuntimeException e){
            return new ModelAndView("exception", "msg", e.getMessage());
            //model.addAttribute("msg", e.getMessage());
            //return "exception";
        }
        return showAllReservations();
        //return "redirect:/reservations/";
    }

    @RequestMapping
    public ModelAndView showAllReservations(){
        return new ModelAndView("allReservation", "reservation", reservationService.getAllReservations());
    }

    @RequestMapping("/delete-reservation/{id}")
    public String deleteReservation(@PathVariable String id){
        reservationService.deleteReservation(id);
        return "redirect:/reservations/";
    }

    @RequestMapping("/end-reservation/{id}")
    public ModelAndView endReservation(@PathVariable String id){
        reservationService.endReservation(id, LocalDateTime.now());
        ModelAndView model = new ModelAndView("endReservation", "reservation", reservationService.getReservation(id));
        model.addObject("price", reservationService.countReservationPrice(id));
        return model;
    }

}
