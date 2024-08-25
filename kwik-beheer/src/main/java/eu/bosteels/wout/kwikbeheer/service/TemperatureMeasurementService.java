package eu.bosteels.wout.kwikbeheer.service;

import eu.bosteels.wout.kwikbeheer.model.Room;
import eu.bosteels.wout.kwikbeheer.model.TemperatureMeasurement;
import eu.bosteels.wout.kwikbeheer.repository.TemperatureMeasurementRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TemperatureMeasurementService {

    private static final Logger log = LoggerFactory.getLogger(TemperatureMeasurementService.class);

    @Autowired
    private TemperatureMeasurementRepository temperatureMeasurementRepository;

    @Autowired
    private RoomService roomService;

    public List<TemperatureMeasurement> getAllTemperatures() {
        return temperatureMeasurementRepository.findAll();
    }

    public void saveTemperatureMeasurement(String buildingName, String roomNane, float celsius) {
        Optional<Room> optRoom = roomService.getRoomIfExists(buildingName, roomNane);
        optRoom.ifPresent(
                room -> temperatureMeasurementRepository.save(new TemperatureMeasurement(room, celsius))
        );
    }
}
