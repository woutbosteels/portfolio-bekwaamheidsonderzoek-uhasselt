package eu.bosteels.wout.kwikbeheer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class TemperatureReading {

    private float celsius;

    public TemperatureReading() {
    }

    public TemperatureReading(float f) {
        this.celsius = f;
    }

    @JsonIgnore
    public float getFahrenheit() {
        return (celsius * 1.8f) + 32;
    }

    public float getCelsius() {
        return celsius;
    }

    public void setCelsius(float celsius) {
        this.celsius = celsius;
    }
}
