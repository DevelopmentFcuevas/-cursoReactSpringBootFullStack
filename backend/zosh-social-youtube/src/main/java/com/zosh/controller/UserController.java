package com.zosh.controller;

import com.zosh.model.User;
import com.zosh.repository.UserRepository;
import com.zosh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    UserRepository repository;
    @Autowired
    UserService service;

    //Endpoints publicos
    /*@PostMapping("/users")
    public User createUser(@RequestBody User user) {
        //User newUser = new User();
        //newUser.setId(user.getId());
        //newUser.setEmail(user.getEmail());
        //newUser.setFirstName(user.getFirstName());
        //newUser.setLastName(user.getLastName());
        //newUser.setPassword(user.getPassword());
        //newUser.setGender(user.getGender());
        //User savedUser = repository.save(newUser);
        //return savedUser;

        User savedUser = service.registerUser(user);
        return savedUser;
    }*/

    @DeleteMapping("/users/{userId}")
    public String deleteUser(@PathVariable("userId") Long id) throws Exception {
        Optional<User> user = repository.findById(id);
        if (user.isEmpty()) {
            throw new Exception("user not exist with userId: " + id);
        }
        repository.delete(user.get());

        return "user deleted succesfully with id: "+ id;
    }




    //Endpoints privados(Que necesitan autenticarse para poder acceder a ellos)
    @GetMapping("/api/users")
    //@GetMapping("/users")
    public List<User> getUsers() {
        List<User> users = repository.findAll();
        return users;
    }

    @GetMapping("/api/users/{userId}")
    //@GetMapping("/users/{userId}")
    public User getUserById(@PathVariable("userId") Long id) throws Exception {
        //Optional<User> user = repository.findById(id);
        //if (user.isPresent()) {
        //    return user.get();
        //}
        //throw new Exception("user not exist with userId " + id);

        User user = service.findUserById(id);
        return user;
    }

    //@PutMapping("/users/{userId}")
    @PutMapping("/api/users/{userId}")
    public User updateUser(@RequestBody User user, @PathVariable Long userId) throws Exception {
        User updatedUser = service.updateUser(user, userId);
        return updatedUser;
    }

    @PutMapping("/users/follow/{userId1}/{userId2}")
    //@PutMapping("/api/users/follow/{userId}/{userId2}")
    public User followUserHandler(@PathVariable Long userId1, @PathVariable Long userId2) throws Exception {
        User user = service.followUser(userId1, userId2);
        return user;

        //return service.followUser(userId1, userId2);
    }

    //@GetMapping("/users/search")
    @GetMapping("/api/users/search")
    public List<User> searchUser(@RequestParam("query") String query) {
        List<User> users = service.searchUser(query);
        return users;
    }

}
