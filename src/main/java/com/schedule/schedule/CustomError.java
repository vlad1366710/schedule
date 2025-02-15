package com.schedule.schedule;

public class CustomError {
    private String message;
    private int status;

    public CustomError(String message, int status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }
}
