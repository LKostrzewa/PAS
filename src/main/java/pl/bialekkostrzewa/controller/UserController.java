package pl.bialekkostrzewa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.bialekkostrzewa.model.Client;
import pl.bialekkostrzewa.model.MyUserDetails;
import pl.bialekkostrzewa.service.MyUserDetailsService;
import pl.bialekkostrzewa.service.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;

@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/add-client")
    public ModelAndView showClientForm(){
        MyUserDetails client = new MyUserDetails();
        client.setEnabled(true);
        return new ModelAndView("clientForm", "client", client);
    }

    @PostMapping("/add-client")
    public String addClient(@Valid @ModelAttribute("client") MyUserDetails client, BindingResult bindingResult, AuthenticationManagerBuilder auth){
        if (!bindingResult.hasErrors()){
            //userService.addUser(client);

            userService.addUserToPool2(client,"USER");
            return "redirect:/users/";
        }
        return "clientForm";
        //else {
        //    new ModelAndView("clientForm", "client", client);
        //}
    }

    @RequestMapping("/update-client/{login}")
    public ModelAndView showClientUpdateForm(@PathVariable String login){
        return new ModelAndView("clientUpdateForm", "client", userService.getUser(login));
    }

    @PostMapping("/update-client")
    public String updateClient(@Valid @ModelAttribute Client client, BindingResult bindingResult){
        if (!bindingResult.hasErrors()){
            userService.updateUser(client.getLogin(), client);
            return "redirect:/users/";
        }
        return "clientUpdateForm";
    }

    @RequestMapping
    public ModelAndView showAllClients(){
        return new ModelAndView("allClient", "clients", userService.getAllClients());
    }
}
