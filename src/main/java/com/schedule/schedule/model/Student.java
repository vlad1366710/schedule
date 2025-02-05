package com.schedule.schedule.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;



@Entity
@Table(name = "students")
public class Student {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private StudentGroup group;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    // Getters and Setters
}
