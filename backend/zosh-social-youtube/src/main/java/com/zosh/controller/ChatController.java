package com.zosh.controller;

import com.zosh.model.Chat;
import com.zosh.model.User;
import com.zosh.request.CreateChatRequest;
import com.zosh.service.ChatService;
import com.zosh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChatController {
    @Autowired
    private ChatService service;
    @Autowired
    private UserService userService;

    @PostMapping("/api/chats")
    public Chat createChat(@RequestHeader("Authorization") String jwt,
                           @RequestBody CreateChatRequest request) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);//recupera un usuario por el token que se le pasa como parámetro.

        User user2 = userService.findUserById(request.getUserId());
        Chat chat = service.createChat(reqUser, user2);
        return chat;
    }

    @GetMapping("/api/chats")
    public List<Chat> findUsersChat(@RequestHeader("Authorization") String jwt) {
        User reqUser = userService.findUserByJwt(jwt);//recupera un usuario por el token que se le pasa como parámetro.

        List<Chat> chats = service.findUsersChat(reqUser.getId());
        return chats;
    }

}
