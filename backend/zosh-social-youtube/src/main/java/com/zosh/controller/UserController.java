package com.zosh.controller;

import com.zosh.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @GetMapping("/users")
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        User user1 = new User("code", "zosh", "codewithzosh@gmail.com", "123");
        users.add(user1);
        return users;
    }

}
