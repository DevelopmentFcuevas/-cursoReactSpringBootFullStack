package com.zosh.controller;

import com.zosh.model.Message;
import com.zosh.model.User;
import com.zosh.service.MessageService;
import com.zosh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CreateMessage {
    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;

    @PostMapping("/api/messages/chat/{chatId}")
    public Message createMessage(@RequestHeader("Authorization") String jwt,
                                 @RequestBody Message request,
                                 @PathVariable Long chatId) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);//recupera un usuario por el token que se le pasa como parámetro.

        Message message = messageService.createMessage(reqUser, chatId, request);
        return message;
    }

    @GetMapping("/api/messages/chat/{chatId}")
    public List<Message> findChatsMessage(@RequestHeader("Authorization") String jwt,
                                      @PathVariable Long chatId) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);//recupera un usuario por el token que se le pasa como parámetro.

        List<Message> messages = messageService.findChatsMessages(chatId);
        return messages;
    }

}
