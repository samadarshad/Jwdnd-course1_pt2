package com.udacity.jdnd.course1.course1_pt2.model;

public class ChatMessage {
    public String username, message; // does thymeleaf not use my getters and setters?

    public void setUsername(String username) {
        this.username = username;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
