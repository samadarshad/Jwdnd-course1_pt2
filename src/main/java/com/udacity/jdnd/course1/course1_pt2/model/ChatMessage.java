package com.udacity.jdnd.course1.course1_pt2.model;

public class ChatMessage {
    private String username;
    private String messageText;

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    private Integer messageId;

    public String getUsername() {
        return username;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

//    public ChatMessage(String username, String messageText, Integer messageid) {
//        this.username = username;
//        this.messageText = messageText;
//        this.messageid = messageid;
//    }
//
//    public String username, message; // does thymeleaf not use my getters and setters?
//
    public void setUsername(String username) {
        this.username = username;
    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }

}
