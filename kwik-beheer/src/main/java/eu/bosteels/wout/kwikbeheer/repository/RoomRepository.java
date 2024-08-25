package eu.bosteels.wout.kwikbeheer.repository;

import eu.bosteels.wout.kwikbeheer.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {

    Optional<Room> findRoomByRoomNameAndBuildingBuildingName(String room, String building);
}
