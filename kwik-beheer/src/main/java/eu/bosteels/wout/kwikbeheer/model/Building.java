package eu.bosteels.wout.kwikbeheer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Building {

    @Id
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
