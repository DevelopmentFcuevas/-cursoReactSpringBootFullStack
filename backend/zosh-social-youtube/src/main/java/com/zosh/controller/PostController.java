package com.zosh.controller;

import com.zosh.model.Post;
import com.zosh.model.User;
import com.zosh.response.ApiResponse;
import com.zosh.service.PostService;
import com.zosh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {
    @Autowired
    PostService service;
    @Autowired
    UserService userService;

    //@PostMapping("/posts/user/{userId}")
    //public ResponseEntity<Post> createPost(@RequestBody Post post, @PathVariable Long userId) throws Exception {
    //    Post createdPost = service.createNewPost(post, userId);
    //    return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    //}
    @PostMapping("/api/posts")
    public ResponseEntity<Post> createPost(@RequestHeader("Authorization") String jwt,
                                           @RequestBody Post post) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);

        Post createdPost = service.createNewPost(post, reqUser.getId());
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    //@DeleteMapping("/posts/{postId}/user/{userId}")
    //public ResponseEntity<ApiResponse> deletePost(@PathVariable Long postId, @PathVariable Long userId) throws Exception {
    //    String message = service.deletePost(postId, userId);
    //    ApiResponse response = new ApiResponse(message, true);

    //    return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
    //}
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@RequestHeader("Authorization") String jwt,
                                                  @PathVariable Long postId) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);

        String message = service.deletePost(postId, reqUser.getId());
        ApiResponse response = new ApiResponse(message, true);

        return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<Post> findPostByIdHandler(@PathVariable Long postId) throws Exception {
        Post post = service.findPostById(postId);
        return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
    }

    @GetMapping("/posts/user/{userId}")
    public ResponseEntity<List<Post>> findUsersPost(@PathVariable Long userId) {
        List<Post> posts = service.findPostByUserId(userId);
        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> findAllPost() {
        List<Post> posts = service.findAllPost();
        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
    }

    //@PutMapping("/posts/save/{postId}/user/{userId}")
    //public ResponseEntity<Post> savedPostHandler(@PathVariable Long postId, @PathVariable Long userId) throws Exception {
    //    Post post = service.savedPost(postId, userId);
    //    return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
    //}
    @PutMapping("/posts/save/{postId}")
    public ResponseEntity<Post> savedPostHandler(@RequestHeader("Authorization") String jwt,
                                                 @PathVariable Long postId) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);

        Post post = service.savedPost(postId, reqUser.getId());
        return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
    }

    //@PutMapping("/posts/like/{postId}/user/{userId}")
    //public ResponseEntity<Post> likePostHandler(@PathVariable Long postId, @PathVariable Long userId) throws Exception {
    //    Post post = service.likePost(postId, userId);
    //    return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
    //}
    @PutMapping("/posts/like/{postId}")
    public ResponseEntity<Post> likePostHandler(@RequestHeader("Authorization") String jwt,
                                                @PathVariable Long postId) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);

        Post post = service.likePost(postId, reqUser.getId());
        return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
    }

}
