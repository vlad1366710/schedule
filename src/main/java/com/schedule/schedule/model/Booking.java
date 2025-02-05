package com.schedule.schedule.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    @NotNull(message = "Аудитория не может быть пустой")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    @NotNull(message = "Преподаватель не может быть пустым")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    @NotNull(message = "Группа не может быть пустой")
    private StudentGroup group;

    @ManyToOne
    @JoinColumn(name = "class_time_id", nullable = false)
    @NotNull(message = "Время занятия не может быть пустым")
    private ClassTime classTime;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // Добавлено поле user
    @NotNull(message = "Пользователь не может быть пустым")
    private Users user;

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public StudentGroup getGroup() {
        return group;
    }

    public void setGroup(StudentGroup group) {
        this.group = group;
    }

    public ClassTime getClassTime() {
        return classTime;
    }

    public void setClassTime(ClassTime classTime) {
        this.classTime = classTime;
    }

    // Получение времени начала и окончания занятия
    public LocalDateTime getStartTime() {
        return classTime != null ? classTime.getStartTime() : null;
    }

    public LocalDateTime getEndTime() {
        return classTime != null ? classTime.getEndTime() : null;
    }
}