package com.zosh.controller;

import com.zosh.model.User;
import com.zosh.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserRepository repository;

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        User newUser = new User();
        newUser.setId(user.getId());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());

        User savedUser = repository.save(newUser);

        return savedUser;
    }


    @GetMapping("/users")
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        User user1 = new User(1L, "code", "zosh", "codewithzosh@gmail.com", "123");
        users.add(user1);
        return users;
    }

    @GetMapping("/users/{userId}")
    public User getUserById(@PathVariable("userId") Long id) {
        User user1 = new User(1L, "code", "zosh", "codewithzosh@gmail.com", "123");
        user1.setId(id);
        return user1;
    }



    @PutMapping("/users")
    public User updateUser(@RequestBody User user) {
        User user1 = new User(1L, "code", "zosh", "codewithzosh@gmail.com", "123");
        if (user.getFirstName() != null) {
            user1.setFirstName(user.getFirstName());
        }

        if (user.getLastName() != null) {
            user1.setLastName(user.getLastName());
        }

        if (user.getEmail() != null) {
            user1.setEmail(user.getEmail());
        }

        return user1;
    }

    @DeleteMapping("/users/{userId}")
    public String deleteUser(@PathVariable("userId") Long id) {
        return "user deleted succesfully with id: "+ id;
    }

}
