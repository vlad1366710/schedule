package com.schedule.schedule.model;

import jakarta.persistence.*;
import java.util.List;

/**
 * Класс, представляющий факультет университета.
 */
@Entity
@Table(name = "faculties")
public class Faculty {

    // Уникальный идентификатор факультета
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Название факультета
    private String name;

    // Список курсов, связанных с факультетом
    @OneToMany(mappedBy = "faculty")
    private List<Speciality> speciality;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Speciality> getCourses() {
        return speciality;
    }

    public void setCourses(List<Speciality> speciality) {
        this.speciality = speciality;
    }
}
