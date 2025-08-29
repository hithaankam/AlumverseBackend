package com.alumverse.alumverse.controller;

import java.util.List;


import com.alumverse.alumverse.dto.PostDto;
import com.alumverse.alumverse.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin("*")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        PostDto createdPost = postService.createPost(postDto);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts() {
        List<PostDto> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @PostMapping("/{id}/like")
    public ResponseEntity<PostDto> likePost(@PathVariable String id, @RequestBody String alumniId) {
        // You would normally get the alumniId from the authenticated user's context
        PostDto updatedPost = postService.likePost(id, alumniId);
        return ResponseEntity.ok(updatedPost);
    }
}