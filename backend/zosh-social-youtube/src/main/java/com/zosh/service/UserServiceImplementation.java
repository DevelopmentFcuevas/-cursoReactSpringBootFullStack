package com.zosh.service;

import com.zosh.model.User;
import com.zosh.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {
    @Autowired
    private UserRepository repository;

    @Override
    public User registerUser(User user) {
        User newUser = new User();
        newUser.setId(user.getId());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());

        return repository.save(newUser);
    }

    @Override
    public User findUserById(Long userId) throws Exception {
        Optional<User> user = repository.findById(userId);
        if (user.isPresent()) {
            return user.get();
        }
        throw new Exception("user not exist with userId: " + userId);
    }

    @Override
    public User findUserByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public User followUser(Long userId, Long userId2) throws Exception {
        User user = findUserById(userId);
        User user2 = findUserById(userId2);

        user2.getFollowers().add(user.getId());
        user.getFollowings().add(user2.getId());

        repository.save(user);
        repository.save(user2);

        return user;
    }

    @Override
    public User updateUser(User user, Long userId) throws Exception {
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

    @Override
    public List<User> searchUser(String query) {
        return repository.searchUser(query);
    }
}
