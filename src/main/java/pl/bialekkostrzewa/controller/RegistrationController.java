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
import pl.bialekkostrzewa.service.MyUserDetailsService;
import pl.bialekkostrzewa.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

//@RequestMapping("")
@Controller
public class RegistrationController {

    private UserService userService;
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setMyUserDetailsService(MyUserDetailsService myUserDetailsService) {
        this.myUserDetailsService = myUserDetailsService;
    }

    @RequestMapping
    public String showMainPage(ModelMap model) {
        model.addAttribute("message", "Welcome in our restaurant");
        return "index";
    }

    @RequestMapping("/default")
    public String defaultAfterLogin(HttpServletRequest request) {
        if (request.isUserInRole("ADMIN")) {
            return "redirect:/users/";
        } else if (request.isUserInRole("MANAGER")) {
            return "redirect:/resources/";
        } else return "redirect:/reservations/";
    }

    @RequestMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @RequestMapping("/register")
    public ModelAndView showRegisterPage() {
        Client client = new Client();
        client.setEnabled(false);
        return new ModelAndView("register", "user", client);
    }

    @PostMapping("/add-client")
    public String addClient(@Valid @ModelAttribute("user") Client client, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            userService.addUser(client);
            //myUserDetailsService.addUser(client);
            return "redirect:/reservations/";
        }
        return "register";
    }
}
