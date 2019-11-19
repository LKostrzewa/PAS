package pl.bialekkostrzewa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.bialekkostrzewa.model.Client;
import pl.bialekkostrzewa.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("add-client")
    public ModelAndView showClientForm(){
        return new ModelAndView("clientForm", "client", new Client());
    }

    @PostMapping("add-client")
    public String addClient(@Valid @ModelAttribute("client") Client client){
        userService.addUser(client);
        return "clientForm";
    }

    //TODO tutaj luzna sugestia pewnie widok sie zmieni dla upozadkowania
    @RequestMapping("all-clients")
    public ModelAndView showAllClients(){
        return new ModelAndView("allResource", "resource", userService.getAllClients());
    }
}
