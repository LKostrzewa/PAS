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
        resourceService.addResource(new BallRoom("testBallRoom", 10,"JakisTekst",5));
        resourceService.addResource(new Table("test", 10,10,10));
    }

    @PostMapping("/add-table")
    public ResponseEntity addTable(@RequestBody Table resource, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            resourceService.addResource(resource);
            return new ResponseEntity(HttpStatus.CREATED);
            //return "redirect:/resources/";
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
        //return "tableForm";
    }

    @PostMapping("/add-room")
    public void addBallRoom(@Valid @RequestBody BallRoom resource, BindingResult bindingResult) {
        if ( !bindingResult.hasErrors()) {
            resourceService.addResource(resource);
            //return "redirect:/resources/";
        }
        //return "ballRoomForm";
    }

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

    @DeleteMapping("/delete-resource/{id}")
    public void deleteResource(@PathVariable String id) {
        resourceService.deleteResource(id);
        //return "redirect:/resources/";
    }

    @PutMapping("/update-resource/{id}")
    public void updateResource(@PathVariable String id, @Valid @RequestBody Resource resource){
        if(id.equals(resource.getId())){
            resourceService.updateResource(resource.getId(), resource);
        }
    }
}
