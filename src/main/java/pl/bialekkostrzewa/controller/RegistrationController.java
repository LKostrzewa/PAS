package pl.bialekkostrzewa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.bialekkostrzewa.model.Client;
import pl.bialekkostrzewa.service.UserService;

//@RequestMapping("")
@Controller
public class RegistrationController {

    private UserService userService;

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
}
