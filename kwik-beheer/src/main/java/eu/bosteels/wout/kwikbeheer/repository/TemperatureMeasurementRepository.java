package eu.bosteels.wout.kwikbeheer.repository;

import eu.bosteels.wout.kwikbeheer.model.TemperatureMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemperatureMeasurementRepository extends JpaRepository<TemperatureMeasurement, Long> {


}
