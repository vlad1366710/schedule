package com.schedule.schedule.repository;


import com.schedule.schedule.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    // Вы можете добавить дополнительные методы для поиска по группам, курсам и т.д.
}
