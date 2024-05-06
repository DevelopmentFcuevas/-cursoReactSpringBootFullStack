package com.zosh.service;

import com.zosh.exceptions.UserException;
import com.zosh.model.User;

import java.util.List;

public interface UserService {
    public User registerUser(User user);
    public User findUserById(Long userId) throws UserException;
    public User findUserByEmail(String email);
    public User followUser(Long userId, Long userId2) throws UserException;
    public User updateUser(User user, Long userId) throws UserException;
    public List<User> searchUser(String query);
    public User findUserByJwt(String jwt);
}
