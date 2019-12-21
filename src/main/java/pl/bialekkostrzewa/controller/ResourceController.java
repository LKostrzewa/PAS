package pl.bialekkostrzewa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public ModelAndView showBallRoomForm() {
        return new ModelAndView("ballRoomForm", "ballRoom", new BallRoom());
    }

    @PostMapping("/add-table")
    public String addTable(@Valid @ModelAttribute Table resource, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            resourceService.addResource(resource);
            return "redirect:/resources/";
        }
        return "tableForm";
    }

    @PostMapping("/add-room")
    public String addBallRoom(@Valid @ModelAttribute BallRoom resource, BindingResult bindingResult) {
        if ( !bindingResult.hasErrors()) {
            resourceService.addResource(resource);
            return "redirect:/resources/";
        }
        return "ballRoomForm";
    }

    @RequestMapping
    public ModelAndView showAllResources() {
        return new ModelAndView("allResource", "resource", resourceService.getAllResources());
    }

    @RequestMapping("/all-tables")
    public ModelAndView showAllTables() {
        return new ModelAndView("allResource", "resource", resourceService.getAllTables());
    }

    @RequestMapping("/all-rooms")
    public ModelAndView showAllBallRoom() {
        return new ModelAndView("allResource", "resource", resourceService.getAllBallRoom());
    }

    @RequestMapping("/delete-resource/{id}")
    public String deleteResource(@PathVariable String id) {
        resourceService.deleteResource(id);
        return "redirect:/resources/";
    }

    @RequestMapping("/update-resource/{id}")
    public ModelAndView showUpdateForm(@PathVariable String id) {
        Resource resource = resourceService.getResource(id);
        if (resource instanceof Table) {
            return showUpdateTableForm((Table) resource);
        } else {
            return showUpdateBallRoomForm((BallRoom) resource);
        }
    }

    private ModelAndView showUpdateTableForm(Table table) {
        return new ModelAndView("tableUpdateForm", "table", table);
    }

    @PostMapping("/update-table")
    public String updateTable(@Valid @ModelAttribute Table table, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("res", table);
            return "tableUpdateForm";
        }
        resourceService.updateResource(table.getId(), table);
        return "redirect:/resources/";
    }

    private ModelAndView showUpdateBallRoomForm(BallRoom ballRoom) {
        return new ModelAndView("ballRoomUpdateForm", "room", ballRoom);
    }

    @PostMapping("/update-room")
    public String updateBallRoom(@Valid @ModelAttribute BallRoom ballRoom, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("res", ballRoom);
            return "ballRoomUpdateForm";
        }
        resourceService.updateResource(ballRoom.getId(), ballRoom);
        return "redirect:/resources/";
    }
}
