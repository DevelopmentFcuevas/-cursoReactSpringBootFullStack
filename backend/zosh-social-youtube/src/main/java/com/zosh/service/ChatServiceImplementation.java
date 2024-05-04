package com.zosh.service;

import com.zosh.model.Chat;
import com.zosh.model.User;
import com.zosh.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ChatServiceImplementation implements ChatService {
    @Autowired
    private ChatRepository repository;

    @Override
    public Chat createChat(User reqUser, User user2) {
        Chat isExist = repository.findChatByUsersId(user2, reqUser);
        if (isExist != null) {
            return isExist;
        }

        Chat chat = new Chat();
        chat.getUsers().add(user2);
        chat.getUsers().add(reqUser);
        chat.setTimeStamp(LocalDateTime.now());
        return repository.save(chat);
    }

    @Override
    public Chat findChatById(Long chatId) throws Exception {
        Optional<Chat> opt = repository.findById(chatId);
        if (opt.isEmpty()) {
            throw new Exception("chat not found with id - " + chatId);
        }

        return opt.get();
    }

    @Override
    public List<Chat> findUsersChat(Long userId) {
        return repository.findByUsersId(userId);
    }
}
