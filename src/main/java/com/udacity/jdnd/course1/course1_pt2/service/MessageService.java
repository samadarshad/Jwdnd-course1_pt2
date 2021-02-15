package com.udacity.jdnd.course1.course1_pt2.service;

import com.udacity.jdnd.course1.course1_pt2.Mapper.MessageMapper;
import com.udacity.jdnd.course1.course1_pt2.model.ChatForm;
import com.udacity.jdnd.course1.course1_pt2.model.ChatMessage;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    private final MessageMapper messageMapper;

    public MessageService(MessageMapper messageMapper) {
        this.messageMapper = messageMapper;
    }

    private List<ChatMessage> chatMessagesList;

    public void addChatFormMessage(ChatForm chatForm) {
        ChatMessage newMessage = new ChatMessage();
        newMessage.setUsername(chatForm.getUsername());
        switch (chatForm.getMessageType()) {
            case "Say":
                newMessage.setMessageText(chatForm.getMessageText());
                break;
            case "Whisper":
                newMessage.setMessageText(chatForm.getMessageText().toLowerCase());
                break;
            case "Shout":
                newMessage.setMessageText(chatForm.getMessageText().toUpperCase());
                break;
        }
        messageMapper.insert(newMessage);
        chatMessagesList.add(newMessage);

    }

    @PostConstruct
    public void postConstruct() {
        this.chatMessagesList = new ArrayList<>();
    }

    public List<ChatMessage> getChatMessagesList() {
        return new ArrayList<>(this.messageMapper.getMessages());
//        return new ArrayList<>(this.chatMessagesList);
    }
}
