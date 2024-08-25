package eu.bosteels.wout.kwikbeheer.service;

import eu.bosteels.wout.kwikbeheer.model.Room;
import eu.bosteels.wout.kwikbeheer.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    RoomRepository roomRepository;

    public Optional<Room> getRoomIfExists(String buildingName, String roomNane) {
        return roomRepository.findRoomByRoomNameAndBuildingBuildingName(buildingName, roomNane);
    }
}
