package eu.bosteels.wout.kwikbeheer.service;

import eu.bosteels.wout.kwikbeheer.model.TemperatureMeasurement;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TemperatureService {

    private List<TemperatureMeasurement> temperatures = new ArrayList<>();

    public List<TemperatureMeasurement> getAllTemperatures() {
        return temperatures;
    }

    public void addTemperature(TemperatureMeasurement temperature) {
        temperatures.add(temperature);
    }
}
