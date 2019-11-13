package pl.bialekkostrzewa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.bialekkostrzewa.model.Table;
import pl.bialekkostrzewa.service.ResourceService;

import javax.validation.Valid;

@Controller
@RequestMapping("/add-resource")
public class ResourceController {

    private ResourceService resourceService;

    @Autowired
    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @RequestMapping
    public ModelAndView showForm() {
        return new ModelAndView("resourceForm", "table", new Table());
    }

    @PostMapping("table-added")
    //@ResponseBody
    public String tableForm(@Valid @ModelAttribute Table table, ModelMap model){
        resourceService.addTable(table);
        model.addAttribute("table", table);
        return "resourceShow";
    }
}
