package com.zosh.service;

import com.zosh.model.Comment;
import com.zosh.model.Post;
import com.zosh.model.User;
import com.zosh.repository.CommentRepository;
import com.zosh.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CommentServiceImplementation implements CommentService {
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentRepository repository;
    @Autowired
    private PostRepository postRepository;

    @Override
    public Comment createComment(Comment comment, Long postId, Long userId) throws Exception {
        User user = userService.findUserById(userId);
        Post post = postService.findPostById(postId);

        comment.setUser(user);
        comment.setContent(comment.getContent());
        comment.setCreatedAt(LocalDateTime.now());

        Comment savedComment = repository.save(comment);
        post.getComments().add(savedComment);

        postRepository.save(post);

        return savedComment;
    }

    @Override
    public Comment findCommentById(Long commentId) throws Exception {
        Optional<Comment> opt = repository.findById(commentId);
        if (opt.isEmpty()) {
            throw new Exception("comment not exist");
        }
        return opt.get();
    }

    @Override
    public Comment likeComment(Long commentId, Long userId) throws Exception {
        Comment comment = findCommentById(commentId);
        User user = userService.findUserById(userId);

        if (!comment.getLiked().contains(user)) {
            comment.getLiked().add(user);
        } else {
            comment.getLiked().remove(user);
        }
        return repository.save(comment);
    }
}
