package com.schedule.schedule.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

@Entity
public class Room {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number; // Номер аудитории

    private int capacity; // Вместимость аудитории

    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building; // Привязка к корпусу

    // Геттер и сеттер для id
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    // Геттер и сеттер для number
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    // Геттер и сеттер для capacity
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    // Геттер и сеттер для building
    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }
}
