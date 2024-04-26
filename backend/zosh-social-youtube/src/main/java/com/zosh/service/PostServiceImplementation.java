package com.zosh.service;

import com.zosh.model.Post;
import com.zosh.model.User;
import com.zosh.repository.PostRepository;
import com.zosh.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImplementation implements PostService {
    @Autowired
    private PostRepository repository;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Post createNewPost(Post post, Long userId) throws Exception {
        User user = userService.findUserById(userId);
        Post newPost = new Post();

        newPost.setCaption(post.getCaption());
        newPost.setImage(post.getImage());
        LocalDateTime fechaHora = LocalDateTime.now();
        newPost.setCreatedAt(fechaHora);
        newPost.setVideo(post.getVideo());
        newPost.setUser(user);

        return repository.save(newPost);
    }

    @Override
    public String deletePost(Long postId, Long userId) throws Exception {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);

        if (post.getUser().getId() != user.getId()) {
            throw new Exception("you can't delete another users post");
        }

        repository.delete(post);
        return "post deleted succesfully";

    }

    @Override
    public List<Post> findPostByUserId(Long userId) {
        return repository.findPostByUserId(userId);
    }

    @Override
    public Post findPostById(Long postId) throws Exception {
        Optional<Post> opt = repository.findById(postId);
        if (opt.isEmpty()) {
            throw new Exception("Post not found with id " + postId);
        }
        return opt.get();
    }

    @GetMapping("/posts")
    @Override
    public List<Post> findAllPost() {
        return repository.findAll();
    }

    @Override
    public Post savedPost(Long postId, Long userId) throws Exception {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);

        if (user.getSavedPost().contains(post)) {
            user.getSavedPost().remove(post);
        } else {
            user.getSavedPost().add(post);
        }
        userRepository.save(user);

        return post;
    }

    @Override
    public Post likePost(Long postId, Long userId) throws Exception {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);

        if (post.getLiked().contains(user)) {
            post.getLiked().remove(user);
        } else {
            post.getLiked().add(user);
        }

        return repository.save(post);
    }

}
