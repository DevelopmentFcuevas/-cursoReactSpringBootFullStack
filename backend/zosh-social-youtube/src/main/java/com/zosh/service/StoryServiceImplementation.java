package com.zosh.service;

import com.zosh.model.Story;
import com.zosh.model.User;
import com.zosh.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StoryServiceImplementation implements StoryService {
    @Autowired
    private StoryRepository repository;
    @Autowired
    private UserService userService;

    @Override
    public Story createStory(Story story, User user) {
        Story createdStory = new Story();

        createdStory.setCaptions(story.getCaptions());
        createdStory.setImage(story.getImage());
        createdStory.setUser(user);
        createdStory.setTimeStamp(LocalDateTime.now());

        return repository.save(createdStory);
    }

    @Override
    public List<Story> findStoryByUserId(Long userId) throws Exception {
        User user = userService.findUserById(userId);

        return repository.findByUserId(userId);
    }

}
