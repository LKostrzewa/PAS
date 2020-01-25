package pl.bialekkostrzewa.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.bialekkostrzewa.model.BallRoom;
import pl.bialekkostrzewa.model.Resource;
import pl.bialekkostrzewa.model.Table;
import pl.bialekkostrzewa.service.ResourceService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/resources")
public class ResourceApi {

    private final ResourceService resourceService;

    @Autowired
    public ResourceApi(ResourceService resourceService) {
        this.resourceService = resourceService;
        resourceService.addResource(new Table("test", 10,10,10));
    }

    /*@GetMapping("/add-table")
    public ModelAndView showTableForm() {
        return new ModelAndView("tableForm", "table", new Table());
    }

    @GetMapping("/add-room")
    public ModelAndView showBallRoomForm() {
        return new ModelAndView("ballRoomForm", "ballRoom", new BallRoom());
    }*/

    @PostMapping("/add-table")
    public void addTable(@Valid @ModelAttribute Table resource, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            resourceService.addResource(resource);
            //return "redirect:/resources/";
        }
        //return "tableForm";
    }

    @PostMapping("/add-room")
    public void addBallRoom(@Valid @ModelAttribute BallRoom resource, BindingResult bindingResult) {
        if ( !bindingResult.hasErrors()) {
            resourceService.addResource(resource);
            //return "redirect:/resources/";
        }
        //return "ballRoomForm";
    }

    /*@RequestMapping
    public ModelAndView showAllResources() {
        return new ModelAndView("allResource", "resource", resourceService.getAllResources());
    }*/

    @GetMapping
    public List<Resource> getAllResource(){
        return resourceService.getAllResources();
        //return new ResponseEntity<>(resourceService.getAllResources(), HttpStatus.OK);
    }

    @GetMapping(path = "/all-tables")
    public List<Table> showAllTables() {
        return resourceService.getAllTables();
        //return new ModelAndView("allResource", "resource", resourceService.getAllTables());
    }

    @GetMapping("/all-rooms")
    public List<BallRoom> getAllRooms(){
        return resourceService.getAllBallRoom();
    }

    @GetMapping("/get-resource/{id}")
    public Resource getResource(@PathVariable String id){
        return resourceService.getResource(id);
    }

    /*@RequestMapping("/all-rooms")
    public ModelAndView showAllBallRoom() {
        return new ModelAndView("allResource", "resource", resourceService.getAllBallRoom());
    }*/

    @DeleteMapping("/delete-resource/{id}")
    public void deleteResource(@PathVariable String id) {
        resourceService.deleteResource(id);
        //return "redirect:/resources/";
    }

    @PutMapping("/update-resource/{id}")
    public void updateResource(@PathVariable String id, @Valid @ModelAttribute Resource resource){
        if(id.equals(resource.getId())){
            resourceService.updateResource(resource.getId(), resource);
        }
    }

    /*@RequestMapping("/update-resource/{id}")
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
            //to w obu przypadkach jest chyba nie potrzebne
            model.addAttribute("res", ballRoom);
            return "ballRoomUpdateForm";
        }
        resourceService.updateResource(ballRoom.getId(), ballRoom);
        return "redirect:/resources/";
    }*/
}
