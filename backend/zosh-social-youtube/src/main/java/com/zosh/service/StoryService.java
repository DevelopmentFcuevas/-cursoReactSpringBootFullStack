package com.zosh.service;

import com.zosh.model.Story;
import com.zosh.model.User;

import java.util.List;

public interface StoryService {
    public Story createStory(Story story, User user);
    public List<Story> findStoryByUserId(Long userId) throws Exception;

}
