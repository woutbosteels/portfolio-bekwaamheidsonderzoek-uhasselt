package eu.bosteels.wout.kwikbeheer.controller;

import jakarta.websocket.server.PathParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/chat")
    public String chat() {
        return "chat";
    }

    @GetMapping("/rooms")
    public String rooms(@PathParam("building") String building, Model model){
        model.addAttribute("building", building);
        return "rooms";
    }

}

