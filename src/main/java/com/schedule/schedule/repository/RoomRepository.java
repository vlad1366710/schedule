package com.schedule.schedule.repository;

import com.schedule.schedule.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {}