package com.schedule.schedule.service;

import com.schedule.schedule.model.Room;
import com.schedule.schedule.repository.RoomRepository; // Предполагается, что у вас есть репозиторий для Room
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room getRoomById(Long id) {
        Optional<Room> room = roomRepository.findById(id);
        return room.orElse(null);
    }

    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }

    public Room updateRoom(Long id, Room roomDetails) {
        if (roomRepository.existsById(id)) {
            roomDetails.setId(id);
            return roomRepository.save(roomDetails);
        }
        return null;
    }

    public boolean deleteRoom(Long id) {
        if (roomRepository.existsById(id)) {
            roomRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public List<Room> getRoomsByBuildingId(Long buildingId) {
        // Здесь должен быть код для получения аудиторий по идентификатору здания
        return roomRepository.findByBuildingId(buildingId);
    }

    public List<Room> findByBuildingId(Long buildingId) {
        return roomRepository.findByBuildingId(buildingId); // Метод для поиска аудиторий по идентификатору здания
    }
}
