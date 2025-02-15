package com.schedule.schedule;


public final class RoleConstants {

    // Приватный конструктор, чтобы предотвратить создание экземпляров класса
    private RoleConstants() {
        throw new UnsupportedOperationException("Utility class");
    }

    // Константы для ролей
    public static final String ROLE_TEACHER = "Преподаватель";
    public static final String ROLE_STUDENT = "Студент";
    public static final String ROLE_ADMIN = "Администратор";
    public static final String ROLE_NULL = "";
}