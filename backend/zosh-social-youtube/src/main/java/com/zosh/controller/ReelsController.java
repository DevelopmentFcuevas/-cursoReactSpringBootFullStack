package com.zosh.controller;

import com.zosh.model.Reels;
import com.zosh.model.User;
import com.zosh.service.ReelsService;
import com.zosh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReelsController {
    @Autowired
    private ReelsService service;
    @Autowired
    private UserService userService;

    @PostMapping("/api/reels")
    public Reels createReels(@RequestHeader("Authorization") String jwt,
                             @RequestBody Reels reels) {
        User reqUser = userService.findUserByJwt(jwt);//recupera un usuario por el token que se le pasa como parámetro.
        Reels createdReels = service.createReel(reels, reqUser);

        return createdReels;
    }

    @GetMapping("/api/reels")
    public List<Reels> findAllReels() {
        List<Reels> reels = service.findAllReels();
        return reels;
    }

    @GetMapping("/api/reels/user/{userId}")
    public List<Reels> findUsersReels(@PathVariable Long userId) throws Exception {
        List<Reels> reels = service.findUsersReel(userId);
        return reels;
    }

}
