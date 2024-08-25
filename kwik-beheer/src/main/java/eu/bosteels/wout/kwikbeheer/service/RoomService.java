package eu.bosteels.wout.kwikbeheer.service;

import eu.bosteels.wout.kwikbeheer.model.Room;
import eu.bosteels.wout.kwikbeheer.repository.RoomRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomService {

    private static final Logger log = LoggerFactory.getLogger(RoomService.class);
    @Autowired
    RoomRepository roomRepository;

    public Optional<Room> getRoomIfExists(String buildingName, String roomNane) {
        log.debug(buildingName);
        log.debug(roomNane);
        return roomRepository.findAllByRoomName(roomNane).stream().filter(room -> room.getBuilding().getBuildingName().equalsIgnoreCase(buildingName))
                .findFirst();
    }
}
