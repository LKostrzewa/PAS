package pl.bialekkostrzewa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.bialekkostrzewa.model.Client;
import pl.bialekkostrzewa.service.UserService;

import javax.validation.Valid;

//@RequestMapping("")
@Controller
public class RegistrationController {

    private UserService userService;

    @Autowired
    public RegistrationController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping
    public String showMainPage(ModelMap model){
        model.addAttribute("message", "Welcome in our restaurant");
        return "index";
    }

    @RequestMapping("/login")
    public ModelAndView showLoginPage(){
        return null;
    }

    @RequestMapping("/register")
    public ModelAndView showRegisterPage(){
        Client client = new Client();
        client.setActive(false);
        return new ModelAndView("register", "user", client);
    }

    @PostMapping("/add-client")
    public String addClient(@Valid @ModelAttribute("user") Client client, BindingResult bindingResult){
        if(!bindingResult.hasErrors()){
            userService.addUser(client);
            //for now
            //TODO strona startowa klienta tzn jego rezerwejszyny
            return "register";
        }
        return "register";
    }
}
