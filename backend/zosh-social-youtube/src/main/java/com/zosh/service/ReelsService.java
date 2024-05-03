package com.zosh.service;

import com.zosh.model.Reels;
import com.zosh.model.User;

import java.util.List;

public interface ReelsService {
    public Reels createReel(Reels reels, User user);
    public List<Reels> findAllReels();
    public List<Reels> findUsersReel(Long userId) throws Exception;
}
