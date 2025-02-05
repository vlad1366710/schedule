package com.schedule.schedule.repository;

import com.schedule.schedule.model.Booking;
import com.schedule.schedule.model.ClassTime;
import com.schedule.schedule.model.Room;
import com.schedule.schedule.model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    // Поиск бронирований по аудитории
    List<Booking> findByRoom(Room room);

    // Поиск бронирований по пользователю и временному интервалу
    List<Booking> findByUserAndClassTimeStartTimeBetween(Users user, LocalDateTime startTime, LocalDateTime endTime);

    // Поиск бронирований по пользователю
    List<Booking> findByUser (Users user);

    // Пагинация и сортировка бронирований
    Page<Booking> findAll(Pageable pageable);

    // Поиск бронирований по ID аудитории и ID времени занятия
    List<Booking> findByRoomIdAndClassTimeId(Long roomId, Long classTimeId);

    // Поиск бронирований по ID аудитории
    List<Booking> findByRoomId(Long roomId);

    // Поиск бронирований по ID времени занятия
    List<Booking> findByClassTimeId(Long classTimeId);

    // Поиск бронирований по аудитории и временному интервалу
    List<Booking> findByRoomAndClassTime_StartTimeBetween(Room room, LocalDateTime startTime, LocalDateTime endTime);

    // Поиск бронирований по аудитории и времени занятия
    List<Booking> findByRoomAndClassTime(Room room, ClassTime classTime);
}
