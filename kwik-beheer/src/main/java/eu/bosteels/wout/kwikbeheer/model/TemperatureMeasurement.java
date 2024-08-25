package eu.bosteels.wout.kwikbeheer.model;

import jakarta.persistence.*;

@Entity
public class TemperatureMeasurement {

    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private float celsius;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    public TemperatureMeasurement() {
    }

    public TemperatureMeasurement(Room room, float celsius) {
        this.room = room;
        this.celsius = celsius;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


    public float getCelsius() {
        return celsius;
    }

    public void setCelsius(float celsius) {
        this.celsius = celsius;
    }
}
