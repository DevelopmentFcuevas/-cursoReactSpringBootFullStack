package com.zosh.service;

import com.zosh.model.Chat;
import com.zosh.model.User;

import java.util.List;

public interface ChatService {
    public Chat createChat(User reqUser, User user2);
    public Chat findChatById(Long chatId) throws Exception;
    public List<Chat> findUsersChat(Long userId);
}
