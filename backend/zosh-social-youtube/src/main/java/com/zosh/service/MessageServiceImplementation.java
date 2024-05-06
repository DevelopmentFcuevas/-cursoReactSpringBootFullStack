package com.zosh.service;

import com.zosh.model.Chat;
import com.zosh.model.Message;
import com.zosh.model.User;
import com.zosh.repository.ChatRepository;
import com.zosh.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImplementation implements MessageService {
    @Autowired
    private MessageRepository repository;
    @Autowired
    private ChatService chatService;
    @Autowired
    private ChatRepository chatRepository;

    @Override
    public Message createMessage(User user, Long chatId, Message request) throws Exception {
        Chat chat = chatService.findChatById(chatId);

        Message message = new Message();
        message.setChat(chat);
        message.setContent(request.getContent());
        message.setImage(request.getImage());
        message.setUser(user);
        message.setTimeStamp(LocalDateTime.now());

        Message savedMessage = repository.save(message);

        chat.getMessages().add(savedMessage);
        chatRepository.save(chat);

        return savedMessage;
    }

    @Override
    public List<Message> findChatsMessages(Long chatId) throws Exception {
        Chat chat = chatService.findChatById(chatId);
        return repository.findByChatId(chatId);
    }

}
