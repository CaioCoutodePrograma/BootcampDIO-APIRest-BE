package com.project.bootcamp.exceptions;

public class ExeptionResponse {
    private String message;

    public ExeptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
