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

    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building; // Привязка к корпусу


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }
}
