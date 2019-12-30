package pl.bialekkostrzewa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.bialekkostrzewa.model.Client;
import pl.bialekkostrzewa.model.User;
import pl.bialekkostrzewa.service.UserService;

import javax.validation.Valid;

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
        return new ModelAndView("clientForm", "client", new Client());
    }

    @PostMapping("/add-client")
    public String addClient(@Valid @ModelAttribute("client") Client client, BindingResult bindingResult){
        if (!bindingResult.hasErrors()){
            //userService.addClientFromUser(user);
            userService.addUser(client);
            return "redirect:/users/";
        }
        return "clientForm";
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
