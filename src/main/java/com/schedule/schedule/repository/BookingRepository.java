package com.schedule.schedule.repository;

import com.schedule.schedule.model.Booking;
import com.schedule.schedule.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByRoomAndStartTimeBetween(Room room, LocalDateTime startTime, LocalDateTime endTime);
}
