package com.zosh.controller;

import com.zosh.model.Story;
import com.zosh.model.User;
import com.zosh.service.StoryService;
import com.zosh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StoryController {
    @Autowired
    private StoryService service;
    @Autowired
    private UserService userService;

    @PostMapping("/api/story")
    public Story createStory(@RequestHeader("Authorization") String jwt,
                             @RequestBody Story story) {
        User reqUser = userService.findUserByJwt(jwt);//recupera un usuario por el token que se le pasa como parámetro.

        Story createdStory = service.createStory(story, reqUser);

        return createdStory;
    }

    @GetMapping("/api/story/user/{userId}")
    public List<Story> findUsersStory(@RequestHeader("Authorization") String jwt,
                                      @PathVariable Long userId) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);//recupera un usuario por el token que se le pasa como parámetro.

        List<Story> stories = service.findStoryByUserId(userId);

        return stories;
    }

}
