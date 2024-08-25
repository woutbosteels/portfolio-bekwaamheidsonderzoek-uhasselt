package eu.bosteels.wout.kwikbeheer.controller;

import eu.bosteels.wout.kwikbeheer.service.TemperatureService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TemperatureController {

    private final TemperatureService temperatureService;

    public TemperatureController(TemperatureService temperatureService) {
        this.temperatureService = temperatureService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/htmx")
    public String htmx() {
        return "htmx :: message";
    }

}

