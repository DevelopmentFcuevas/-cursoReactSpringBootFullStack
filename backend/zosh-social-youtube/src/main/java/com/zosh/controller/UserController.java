package com.zosh.controller;

import com.zosh.model.User;
import com.zosh.repository.UserRepository;
import com.zosh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserRepository repository;
    @Autowired
    private UserService service;

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return service.registerUser(user);
    }


    @GetMapping("/users")
    public List<User> getUsers() {
        List<User> users = repository.findAll();
        return users;
    }

    @GetMapping("/users/{userId}")
    public User getUserById(@PathVariable("userId") Long id) throws Exception {
        return service.findUserById(id);
    }


    @PutMapping("/users/{userId}")
    public User updateUser(@RequestBody User user, @PathVariable Long userId) throws Exception {
       return service.updateUser(user, userId);
    }

    @DeleteMapping("/users/{userId}")
    public String deleteUser(@PathVariable("userId") Long id) throws Exception {
        Optional<User> user = repository.findById(id);
        if (user.isEmpty()) {
            throw new Exception("user not exist with userId: " + id);
        }
        repository.delete(user.get());

        return "user deleted succesfully with id: "+ id;
    }

    @PutMapping("/users/follow/{userId}/{userId2}")
    public User followUserHandler(@PathVariable Long userId, @PathVariable Long userId2) throws Exception {
        return service.followUser(userId, userId2);
    }

    @GetMapping("/users/search")
    public List<User> searchUser(@RequestParam("query") String query) {
        return service.searchUser(query);
    }

}
