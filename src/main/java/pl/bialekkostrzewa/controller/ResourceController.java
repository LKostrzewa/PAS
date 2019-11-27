package pl.bialekkostrzewa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.bialekkostrzewa.model.BallRoom;
import pl.bialekkostrzewa.model.Resource;
import pl.bialekkostrzewa.model.Table;
import pl.bialekkostrzewa.service.ResourceService;

import javax.validation.Valid;

@Controller
@RequestMapping("/resources")
public class ResourceController {

    private ResourceService resourceService;

    @Autowired
    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @GetMapping("/add-table")
    public ModelAndView showTableForm() {
        return new ModelAndView("tableForm", "table", new Table());
    }

    @GetMapping("/add-room")
    public ModelAndView showBallRoomForm(){
        return new ModelAndView("ballRoomForm", "ballRoom", new BallRoom());
    }

    @PostMapping("/add-table")
    public String addTable(@Valid @ModelAttribute Table resource){
        resourceService.addResource(resource);
        return "tableForm";
    }

    @PostMapping("add-room")
    public String addBallRoom(@Valid @ModelAttribute BallRoom resource){
        resourceService.addResource(resource);
        return "ballRoomForm";
    }

    @RequestMapping
    public ModelAndView showAllResources(){
        return new ModelAndView("allResource", "resource", resourceService.getAllResources());
    }

    //TODO INNY HTML Napisac do tych wariactw

    @RequestMapping("all-tables")
    public ModelAndView showAllTables(){
        return new ModelAndView("allResource", "resource", resourceService.getAllTables());
    }

    @RequestMapping("all-rooms")
    public ModelAndView showAllBallRoom(){
        return new ModelAndView("allResource", "resource", resourceService.getAllBallRoom());
    }

    @RequestMapping("delete-resource/{id}")
    public String deleteResource(@PathVariable String id){
        resourceService.deleteResource(id);
        return "allResource";
    }

    //TODO Moze jeszcze byc zeby update moze byc robione bez id tzn
    // przekazujemy tylko table i bierzemy jego id jako id do update

    @RequestMapping("update-table/{id}")
    public String updateResource(@PathVariable String id, @Valid @ModelAttribute Table table){
        resourceService.updateResource(id, table);
        return "allResource";
    }

    @RequestMapping("update-room/{id}")
    public String updateBallRoom(@PathVariable String id, @Valid @ModelAttribute BallRoom ballRoom){
        resourceService.updateResource(id, ballRoom);
        return "allResource";
    }
}
