package pl.bialekkostrzewa.controller;

import com.google.gson.Gson;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import pl.bialekkostrzewa.model.BallRoom;
import pl.bialekkostrzewa.model.Resource;
import pl.bialekkostrzewa.model.Table;
import pl.bialekkostrzewa.service.ResourceService;

import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/resources/")
public class ResourceController {

    private String urlBase = "https://localhost:8443/restaurant/api/resources";
    private RestTemplate rest;
    private HttpHeaders headers;

    @Autowired
    public ResourceController(ResourceService resourceService) {
        this.headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "*/*");

        String keyStorePassword = "changeit";
        KeyStore keyStore = null;
        try {
            keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        try {
            keyStore.load(new FileInputStream(new File("C:\\Users\\Lukasz\\.keystore")),
                    keyStorePassword.toCharArray());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        }

        SSLConnectionSocketFactory socketFactory = null;
        try {
            socketFactory = new SSLConnectionSocketFactory(
                    new SSLContextBuilder()
                            .loadTrustMaterial(null, new TrustSelfSignedStrategy())
                            .loadKeyMaterial(keyStore, keyStorePassword.toCharArray())
                            .build(),
                    NoopHostnameVerifier.INSTANCE);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        }

        HttpClient httpClient = HttpClients.custom().setSSLSocketFactory(
                socketFactory).build();

        ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(
                httpClient);
        this.rest = new RestTemplate(requestFactory);
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
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<Table> entity = new HttpEntity<>(resource, headers);
            ResponseEntity<String> test = rest.exchange(urlBase + "/add-table", HttpMethod.POST, entity, String.class);
        //if(test.getStatusCode())
            return "redirect:/resources/";
        }
        return "tableForm";
    }

    @PostMapping("/add-room")
    public String addBallRoom(@Valid @ModelAttribute BallRoom resource, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<BallRoom> entity = new HttpEntity<>(resource, headers);
            rest.exchange(urlBase + "/add-room", HttpMethod.POST, entity, String.class);
            //rest.exchange(urlBase + "/add-room", HttpMethod.POST)
            //resourceService.addResource(resource);
            return "redirect:/resources/";
        }
        return "ballRoomForm";
    }

    /*@RequestMapping
    public ModelAndView showAllResources() {
        return new ModelAndView("allResource", "resource", resourceService.getAllResources());
    }*/

    @RequestMapping
    public ModelAndView showAllResources() {
        //ResponseEntity<Object[]> response = rest.getForEntity(urlBase, Object[].class);
        List<Object> resourceList = rest.exchange(urlBase + "", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Object>>() {
                }).getBody();
        //List<Object> resourceList = Arrays.asList(response.getBody());
        List<Resource> resources = new ArrayList<>();
        for (Object obj : resourceList) {
            resources.add(getFromJson(obj));
        }
        //Resource[] objects = responseEntity.getBody();
        //List<Resource> resourceList = rest.getForEntity(urlBase, Resource[].class);

        //HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);
        //ResponseEntity<String> responseEntity = rest.exchange(urlBase, HttpMethod.GET, requestEntity, String.class);
        //List<Resource> resourceList = rest.getForEntity(urlBase, new HttpEntity<>(), String.class);
        /*List<Resource> resources3 = rest.exchange(urlBase, HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Resource>>() {}).getBody();*/
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
                null, new ParameterizedTypeReference<List<Table>>() {
                }).getBody();
        //return resourceService.getAllTables();
        return new ModelAndView("allResource", "resource", tables);
    }

    @GetMapping("/all-rooms")
    public ModelAndView getAllRooms() {
        List<BallRoom> ballRooms = rest.exchange(urlBase + "/all-rooms", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<BallRoom>>() {
                }).getBody();
        //return resourceService.getAllTables();
        return new ModelAndView("allResource", "resource", ballRooms);
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

    private Resource getFromJson(Object obj) {
        Gson gson = new Gson();
        Map resource = gson.fromJson(obj.toString(), Map.class);
        String id;
        if (resource.get("id") instanceof Double) {
            Double tmp = (Double)resource.get("id");
            id = Integer.toString(tmp.intValue());
        } else {
            id = (String) resource.get("id");
        }
        if (resource.containsKey("numOfPeople")) {
            Double tmp = ((Double) resource.get("number"));
            Double tmp2 = ((Double) resource.get("numOfPeople"));
            return new Table(id, (double) resource.get("price"), tmp.intValue(),
                    tmp2.intValue());
        } else {
            Double tmp = (Double) resource.get("numOfRooms");
            return new BallRoom(id, (double) resource.get("price"), (String) resource.get("description"),
                    tmp.intValue());
        }
    }

    @RequestMapping("/update-resource/{id}")
    public ModelAndView showUpdateForm(@PathVariable String id) {
        //chuj wie czy to bedzie dobrze xdd
        //pewnie nei bo znowu utnie dziada
        //no i ucielo i zrobilem po dziadowemu
        Object obj = rest.exchange(urlBase + "/get-resource/" + id, HttpMethod.GET,
                null, Object.class).getBody();
        Resource resource = getFromJson(obj);
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
    public String updateTable(@Valid @ModelAttribute Table table, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "tableUpdateForm";
        }
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Table> entity = new HttpEntity<>(table, headers);
        rest.exchange(urlBase + "/update-table", HttpMethod.PUT, entity, String.class);
        return "redirect:/resources/";
    }

    private ModelAndView showUpdateBallRoomForm(BallRoom ballRoom) {
        return new ModelAndView("ballRoomUpdateForm", "room", ballRoom);
    }

    @PostMapping("/update-room")
    public String updateBallRoom(@Valid @ModelAttribute BallRoom ballRoom, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "ballRoomUpdateForm";
        }
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<BallRoom> entity = new HttpEntity<>(ballRoom, headers);
        rest.exchange(urlBase + "/update-room", HttpMethod.PUT, entity, String.class);
        return "redirect:/resources/";
    }
}
