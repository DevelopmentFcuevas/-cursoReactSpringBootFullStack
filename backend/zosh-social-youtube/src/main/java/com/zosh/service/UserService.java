package com.zosh.service;

import com.zosh.model.User;

import java.util.List;

public interface UserService {
    public User registerUser(User user);
    public User findUserById(Long userId) throws Exception;
    public User findUserByEmail(String email);
    public User followUser(Long userId, Long userId2) throws Exception;
    public User updateUser(User user, Long userId) throws Exception;
    public List<User> searchUser(String query);
}
