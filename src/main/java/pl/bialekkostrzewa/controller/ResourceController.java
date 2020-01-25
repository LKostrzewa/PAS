package pl.bialekkostrzewa.controller;

import org.apache.logging.log4j.core.appender.rewrite.MapRewritePolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import pl.bialekkostrzewa.model.BallRoom;
import pl.bialekkostrzewa.model.Resource;
import pl.bialekkostrzewa.model.Table;
import pl.bialekkostrzewa.service.ResourceService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/resources")
public class ResourceController {

    private ResourceService resourceService;
    //private final RestTemplate restTemplate;
    //private String urlBase = "https://localhost:8443/restaurant/api/resources";
    private String urlBase = "http://localhost:8080/restaurant/api/resources";
    private RestTemplate rest;
    private HttpHeaders headers;

    @Autowired
    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
        resourceService.addResource(new BallRoom("testBallRoom", 10,"JakisTekst",5));
        this.rest = new RestTemplate();
        this.headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "*/*");
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

    /*@RequestMapping
    public ModelAndView showAllResources() {
        return new ModelAndView("allResource", "resource", resourceService.getAllResources());
    }*/

    @RequestMapping
    public ModelAndView showAllResources(){
        //return resourceService.getAllResources();
        //RestTemplate restTemplate = new RestTemplate();
        //List<Resource> resources = restTemplate.exchange(urlBase, HttpMethod.GET,
        //        null, new ParameterizedTypeReference<List<Resource>>() {}).getBody();
        //List<Resource> resources = restTemplate.getForEntity(urlBase, List<Resource>() {}, HttpMethod.GET);

        //HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);
        //ResponseEntity<String> responseEntity = rest.exchange(urlBase, HttpMethod.GET, requestEntity, String.class);
        List<Resource> resources = rest.exchange(urlBase, HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Resource>>() {}).getBody();
        System.out.println(resources);
        return new ModelAndView("allResource", "resource", resources);
    }


   /*public String get(String uri) {
        HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);
        ResponseEntity<String> responseEntity = rest.exchange(server + uri, HttpMethod.GET, requestEntity, String.class);
        this.setStatus(responseEntity.getStatusCode());
        return responseEntity.getBody();
    }*/

    @GetMapping(path = "/all-tables")
    public ModelAndView showAllTables() {
        List<Table> tables = rest.exchange(urlBase + "/all-tables", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Table>>() {}).getBody();
        //return resourceService.getAllTables();
        return new ModelAndView("allResource", "resource", tables);
    }

    @GetMapping("/all-rooms")
    public ModelAndView getAllRooms(){
        List<BallRoom> ballRooms = rest.exchange(urlBase + "/all-rooms", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<BallRoom>>() {}).getBody();
        //return resourceService.getAllTables();
        return new ModelAndView("allResource", "resource", ballRooms);
    }

    @GetMapping("/get-resource/{id}")
    public Resource getResource(@PathVariable String id){
        //TODO co tutaj?
        return resourceService.getResource(id);
    }

    /*@RequestMapping("/all-rooms")
    public ModelAndView showAllBallRoom() {
        return new ModelAndView("allResource", "resource", resourceService.getAllBallRoom());
    }*/

    @GetMapping("/delete-resource/{id}")
    public String deleteResource(@PathVariable String id) {
        rest.delete(urlBase + "/delete-resource/" + id);
        //resourceService.deleteResource(id);
        return "redirect:/resources/";
    }

    /*@GetMapping("/update-resource/{id}")
    public String updateResource(@PathVariable String id, @Valid @ModelAttribute Resource resource){
        if(id.equals(resource.getId())){
            resourceService.updateResource(resource.getId(), resource);
        }
        return "redirect:/resources/";
    }*/

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
