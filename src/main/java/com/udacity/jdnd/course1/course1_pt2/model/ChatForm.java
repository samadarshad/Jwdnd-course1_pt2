package com.udacity.jdnd.course1.course1_pt2.model;

public class ChatForm {
    public String username;
    public String messageText;
    private String messageType;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) { // does thymeleaf not use my getters and setters?
        this.username = username;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }


}
