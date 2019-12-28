package pl.bialekkostrzewa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.bialekkostrzewa.model.Client;
import pl.bialekkostrzewa.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

//@RequestMapping("")
@Controller
public class RegistrationController {

    private UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping
    public String showMainPage(ModelMap model) {
        model.addAttribute("message", "Welcome in our restaurant");
        return "index";
    }

    @RequestMapping("/default")
    public String defaultAfterLogin(HttpServletRequest request) {
        if (request.isUserInRole("ADMIN")) {
            return "redirect:/users";
        } else if (request.isUserInRole("MANAGER")) {
            return "redirect:/resources";
        } else return "redirect:/reservations";
    }

    @RequestMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @RequestMapping("/register")
    public ModelAndView showRegisterPage() {
        Client client = new Client();
        client.setActive(false);
        return new ModelAndView("register", "user", client);
    }

    @PostMapping("/add-client")
    public String addClient(@Valid @ModelAttribute("user") Client client, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            userService.addUserToPool(client,"ROLE_USER");
            userService.addUser(client);
            return "redirect:/reservations/";
        }
        return "register";
    }
}
