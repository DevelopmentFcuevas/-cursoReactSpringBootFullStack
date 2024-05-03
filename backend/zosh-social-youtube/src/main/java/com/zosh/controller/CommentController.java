package com.zosh.controller;

import com.zosh.model.Comment;
import com.zosh.model.User;
import com.zosh.service.CommentService;
import com.zosh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {
    @Autowired
    private CommentService service;
    @Autowired
    private UserService userService;

    @PostMapping("/api/comments/post/{postId}")
    public Comment createComment(@RequestHeader("Authorization") String jwt,
                                 @RequestBody Comment comment,
                                 @PathVariable("postId") Long postId) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);//recupera un usuario por el token que se le pasa como parámetro.

        Comment createdComment = service.createComment(comment, postId, reqUser.getId());

        return createdComment;
    }

    @PutMapping("/api/comments/like/{commentId}")
    public Comment likeComment(@RequestHeader("Authorization") String jwt,
                               @PathVariable("commentId") Long commentId) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);//recupera un usuario por el token que se le pasa como parámetro.

        Comment likedComment = service.likeComment(commentId, reqUser.getId());

        return likedComment;
    }

}
