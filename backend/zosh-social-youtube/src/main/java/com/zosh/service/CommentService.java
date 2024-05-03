package com.zosh.service;

import com.zosh.model.Comment;

public interface CommentService {
    public Comment createComment(Comment comment, Long postId, Long userId) throws Exception;
    public Comment findCommentById(Long commentId) throws Exception;
    public Comment likeComment(Long commentId, Long userId) throws Exception;
}
