package eu.bosteels.wout.kwikbeheer.controller;

import eu.bosteels.wout.kwikbeheer.service.TemperatureMeasurementService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TemperatureController {

    private final TemperatureMeasurementService temperatureService;

    public TemperatureController(TemperatureMeasurementService temperatureService) {
        this.temperatureService = temperatureService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

}

