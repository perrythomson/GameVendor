package springBootApp.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JsonRestController {

    @RequestMapping("/rest/")
    public String index() {
        return "<h1>Greetings from Game Vendor!</h1>";
    }
}