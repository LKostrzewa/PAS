package pl.bialekkostrzewa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//@RequestMapping("")
@Controller
public class HelloController {

    @RequestMapping
    public String printHello(ModelMap model){
        model.addAttribute("message", "Welcome in our restaurant");
        return "hello";
    }
}
