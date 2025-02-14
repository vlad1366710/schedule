package com.schedule.schedule.controller;

import com.schedule.schedule.model.Room;
import com.schedule.schedule.service.RoomService; // Предполагается, что у вас есть сервис для работы с Room
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms") // Определение базового пути для всех эндпоинтов
public class RoomsController {

    @Autowired
    private RoomService roomService; // Сервис для работы с аудиториями

    // Получить список всех аудиторий или по идентификатору здания
    @GetMapping
    public List<Room> getAllRooms(@RequestParam(required = false) Long buildingId) {
        if (buildingId != null) {
            return roomService.getRoomsByBuildingId(buildingId); // Метод для получения аудиторий по идентификатору здания
        }
        return roomService.getAllRooms(); // Если buildingId не указан, возвращаем все аудитории
    }

    // Получить аудиторию по ID
    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable Long id) {
        Room room = roomService.getRoomById(id);
        if (room != null) {
            return ResponseEntity.ok(room);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Создать новую аудиторию
    @PostMapping
    public Room createRoom(@RequestBody Room room) {
        return roomService.createRoom(room);
    }

    // Обновить существующую аудиторию
    @PutMapping("/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable Long id, @RequestBody Room roomDetails) {
        Room updatedRoom = roomService.updateRoom(id, roomDetails);
        if (updatedRoom != null) {
            return ResponseEntity.ok(updatedRoom);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Удалить аудиторию
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id) {
        boolean isDeleted = roomService.deleteRoom(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
