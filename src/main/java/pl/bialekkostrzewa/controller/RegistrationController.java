package pl.bialekkostrzewa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

//@RequestMapping("")
@Controller
public class RegistrationController {

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
        return null;
    }
}
