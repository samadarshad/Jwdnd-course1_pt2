package com.udacity.jdnd.course1.course1_pt2.controller;

import com.udacity.jdnd.course1.course1_pt2.model.ChatForm;
import com.udacity.jdnd.course1.course1_pt2.model.User;
import com.udacity.jdnd.course1.course1_pt2.service.MessageService;
import com.udacity.jdnd.course1.course1_pt2.service.UserService;
import org.springframework.security.core.Authentication;
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
    private UserService userService;

    public ChatController(MessageService messageService, UserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }

    @GetMapping
    public String getChatPage(@ModelAttribute("chatForm") ChatForm chatForm, Authentication auth, Model model) {
        User user = this.userService.getUser(auth.getName());
        model.addAttribute("username", user.getUsername());
        model.addAttribute("messages", messageService.getChatMessagesList());
        return "chat";
    }

//    @PostMapping
//    public String addMessage(@ModelAttribute("chatForm") ChatForm chatForm, Model model) {
//        messageService.addChatFormMessage(chatForm);
//        model.addAttribute("messages", messageService.getChatMessagesList());
//        return "chat";
//    }

    @PostMapping
    public String addMessage(@ModelAttribute("chatForm") ChatForm chatForm, Authentication auth, Model model) {
        User user = this.userService.getUser(auth.getName());

        chatForm.setUsername(user.getUsername());
        messageService.addChatFormMessage(chatForm);
        model.addAttribute("username", user.getUsername());
        model.addAttribute("messages", messageService.getChatMessagesList());
        return "chat";
    }
}
