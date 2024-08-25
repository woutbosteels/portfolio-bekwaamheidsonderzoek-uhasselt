package eu.bosteels.wout.kwikbeheer.service;

import eu.bosteels.wout.kwikbeheer.model.TemperatureReading;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TemperatureService {

    private List<TemperatureReading> temperatures = new ArrayList<>();

    public List<TemperatureReading> getAllTemperatures() {
        return temperatures;
    }

    public void addTemperature(TemperatureReading temperature) {
        temperatures.add(temperature);

    }
}
