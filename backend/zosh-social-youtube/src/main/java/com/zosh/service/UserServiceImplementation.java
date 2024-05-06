package com.zosh.service;

import com.zosh.config.JwtProvider;
import com.zosh.exceptions.UserException;
import com.zosh.model.User;
import com.zosh.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {
    @Autowired
    UserRepository repository;

    @Override
    public User registerUser(User user) {
        User newUser = new User();
        //newUser.setId(user.getId());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setGender(user.getGender());

        User savedUser = repository.save(newUser);
        return savedUser;
    }

    @Override
    public User findUserById(Long userId) throws UserException {
        Optional<User> user = repository.findById(userId);
        if (user.isPresent()) {
            return user.get();
        }
        throw new UserException("user not exist with userId: " + userId);
    }

    @Override
    public User findUserByEmail(String email) {
        User user = repository.findByEmail(email);
        return user;
    }

    //@Override
    //public User followUser(Long userId1, Long userId2) throws Exception {
    //    User user1 = findUserById(userId1);
    //    User user2 = findUserById(userId2);

    //    user2.getFollowers().add(user1.getId());//Agrega una lista de tipo Long al campo "followers".
    //    user1.getFollowings().add(user2.getId());//Agrega una lista de tipo Long al campo "followings".

    //    repository.save(user1);
    //    repository.save(user2);

    //    return user1;
    //}
    @Override
    public User followUser(Long reqUserId, Long userId2) throws UserException {
        User reqUser = findUserById(reqUserId);
        User user2 = findUserById(userId2);

        user2.getFollowers().add(reqUser.getId());//Agrega una lista de tipo Long al campo "followers".
        reqUser.getFollowings().add(user2.getId());//Agrega una lista de tipo Long al campo "followings".

        repository.save(reqUser);
        repository.save(user2);

        return reqUser;
    }

    @Override
    public User updateUser(User user, Long userId) throws UserException {
        Optional<User> user1 = repository.findById(userId);//representa al usuario actual
        if (user1.isEmpty()) {
            throw new UserException("user not exist with userId: " + userId);
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
        if (user.getGender() != null) {
            oldUser.setGender(user.getGender());
        }

        User updatedUser = repository.save(oldUser);//representa al usuario actualizado.
        return updatedUser;
    }

    @Override
    public List<User> searchUser(String query) {
        return repository.searchUser(query);
    }

    @Override
    public User findUserByJwt(String jwt) {
        String email = JwtProvider.getEmailFromJwtToken(jwt);//Obtiene el email por el token pasado como parametro.

        User user = repository.findByEmail(email);
        return user;
    }
}
