package pl.bialekkostrzewa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.bialekkostrzewa.model.Table;
import pl.bialekkostrzewa.service.ResourceService;

@Controller
@RequestMapping("siusiak")
public class ResourceController {

    private ResourceService resourceService;

    @Autowired
    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @GetMapping
    //@ResponseBody
    public String resourceMajster(Model model){
        resourceService.createTable("ajdi",24.50, 1, 4);
        Table table = (Table)resourceService.getResource("ajdi");
        model.addAttribute("table", table);
        /*Gson gson = new Gson();
        return gson.toJson(resourceService.getResource("ajdi"));*/
        return "resource";
    }
}
