package com.zosh.service;

import com.zosh.model.Message;
import com.zosh.model.User;

import java.util.List;

public interface MessageService {
    public Message createMessage(User user, Long chatId, Message request) throws Exception;
    public List<Message> findChatsMessages(Long chatId) throws Exception;
}
