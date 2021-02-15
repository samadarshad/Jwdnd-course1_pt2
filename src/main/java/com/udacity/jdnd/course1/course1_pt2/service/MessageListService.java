package com.udacity.jdnd.course1.course1_pt2.service;

import com.udacity.jdnd.course1.course1_pt2.model.ChatForm;
import com.udacity.jdnd.course1.course1_pt2.model.ChatMessage;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageListService {

    private List<ChatMessage> chatMessagesList;

    public void addChatFormMessage(ChatForm chatForm) {
        ChatMessage newMessage = new ChatMessage();
        newMessage.setUsername(chatForm.getUsername());
        switch (chatForm.getMessageType()) {
            case "Say":
                newMessage.setMessage(chatForm.getMessageText());
                break;
            case "Whisper":
                newMessage.setMessage(chatForm.getMessageText().toLowerCase());
                break;
            case "Shout":
                newMessage.setMessage(chatForm.getMessageText().toUpperCase());
                break;
        }
        chatMessagesList.add(newMessage);
    }

    @PostConstruct
    public void postConstruct() {
        this.chatMessagesList = new ArrayList<>();
    }

    public List<ChatMessage> getChatMessagesList() {
        return new ArrayList<>(this.chatMessagesList);
    }
}
