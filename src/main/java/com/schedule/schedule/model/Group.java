package com.schedule.schedule.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;


@Entity
@Table(name = "groups")
public class Group {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    // Getters and Setters
}
