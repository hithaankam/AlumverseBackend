package com.alumverse.alumverse.service;

import com.alumverse.alumverse.dto.PostDto;
import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);
    List<PostDto> getAllPosts();
    PostDto likePost(String postId, String alumniId);
}