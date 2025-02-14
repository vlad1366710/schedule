package com.schedule.schedule.repository;

import com.schedule.schedule.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findByBuildingId(Long buildingId);
}