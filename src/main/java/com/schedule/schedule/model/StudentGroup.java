package com.schedule.schedule.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "student_groups")
public class StudentGroup {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int quantity;

    @OneToOne
    @JoinColumn(name = "speciality_id")
    private Speciality speciality;

    @ManyToMany
    @JoinTable(
            name = "student_group_teacher",
            joinColumns = @JoinColumn(name = "student_group_id"),
            inverseJoinColumns = @JoinColumn(name = "users_id")
    )
    private Set<Users> teachers;

    // Геттеры и сеттеры

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

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Set<Users> getTeachers() {
        return teachers; // сломано
    }

    public void setTeachers(Set<Users> teachers) {
        this.teachers = teachers;// сломано
    }
}
