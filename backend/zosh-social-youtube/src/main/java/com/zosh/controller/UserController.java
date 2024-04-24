package com.zosh.controller;

import com.zosh.model.User;
import com.zosh.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        List<User> users = repository.findAll();
        return users;
    }

    @GetMapping("/users/{userId}")
    public User getUserById(@PathVariable("userId") Long id) throws Exception {
        Optional<User> user = repository.findById(id);
        if (user.isPresent()) {
           return user.get();
        }
        throw new Exception("user not exist with userId: " + id);
    }


    @PutMapping("/users/{userId}")
    public User updateUser(@RequestBody User user, @PathVariable Long userId) throws Exception {
        //User user1 = new User(1L, "code", "zosh", "codewithzosh@gmail.com", "123");
        Optional<User> user1 = repository.findById(userId);
        if (user1.isEmpty()) {
            throw new Exception("user not exist with userId: " + userId);
        }

        User oldUser = user1.get();

        if (user.getFirstName() != null) {
            oldUser.setFirstName(user.getFirstName());
        }

        if (user.getLastName() != null) {
            oldUser.setLastName(user.getLastName());
        }

        if (user.getEmail() != null) {
            oldUser.setEmail(user.getEmail());
        }

        User updatedUser = repository.save(oldUser);

        return updatedUser;
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

}
