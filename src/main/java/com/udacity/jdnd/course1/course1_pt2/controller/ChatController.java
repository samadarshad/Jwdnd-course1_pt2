package com.udacity.jdnd.course1.course1_pt2.controller;

import com.udacity.jdnd.course1.course1_pt2.model.ChatForm;
import com.udacity.jdnd.course1.course1_pt2.service.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chat")
public class ChatController {
    private MessageService messageService;

    public ChatController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public String getChatPage(@ModelAttribute("chatForm") ChatForm chatForm, Model model) {
        model.addAttribute("messages", messageService.getChatMessagesList());
        return "chat";
    }

    @PostMapping
    public String addMessage(@ModelAttribute("chatForm") ChatForm chatForm, Model model) {
        messageService.addChatFormMessage(chatForm);
        model.addAttribute("messages", messageService.getChatMessagesList());
        return "chat";
    }
}
