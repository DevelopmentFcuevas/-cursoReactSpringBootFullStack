package com.zosh.service;

import com.zosh.model.Reels;
import com.zosh.model.User;
import com.zosh.repository.ReelsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReelsServiceImplementation implements ReelsService {
    @Autowired
    private ReelsRepository repository;
    @Autowired
    private UserService userService;

    @Override
    public Reels createReel(Reels reels, User user) {
        Reels createReel = new Reels();

        createReel.setTitle(reels.getTitle());
        createReel.setUser(user);
        createReel.setVideo(reels.getVideo());

        return repository.save(createReel);
    }

    @Override
    public List<Reels> findAllReels() {
        return repository.findAll();
    }

    @Override
    public List<Reels> findUsersReel(Long userId) throws Exception {
        userService.findUserById(userId);
        return repository.findByUserId(userId);
    }
}
