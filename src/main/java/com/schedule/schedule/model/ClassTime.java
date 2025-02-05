package com.schedule.schedule.model;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class ClassTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime startTime; // Время начала пары
    private LocalDateTime  endTime;   // Время окончания пары

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime  getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime  startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime  getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime  endTime) {
        this.endTime = endTime;
    }
}
