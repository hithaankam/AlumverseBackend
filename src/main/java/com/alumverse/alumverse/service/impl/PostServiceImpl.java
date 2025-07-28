package com.alumverse.alumverse.service.impl;

import com.alumverse.alumverse.dto.PostDto;
import com.alumverse.alumverse.model.Post;
import com.alumverse.alumverse.repository.PostRepository;
import com.alumverse.alumverse.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = mapToEntity(postDto);
        Post savedPost = postRepository.save(post);
        return mapToDto(savedPost);
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public PostDto likePost(String postId, String alumniId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        // This is a simple implementation. In production, you'd add logic to prevent multiple likes from the same user.
        post.getLikes().add(alumniId);
        Post updatedPost = postRepository.save(post);
        return mapToDto(updatedPost);
    }

    private PostDto mapToDto(Post post) {
        // You would need a PostDto class for this mapping
        // For now, let's just return a simple DTO
        return new PostDto(post.getId(), post.getAuthorId(), post.getContent(), post.getTimestamp(), post.getLikes().size());
    }

    private Post mapToEntity(PostDto postDto) {
        // This is a simple mapping from DTO to entity
        return new Post(postDto.getAuthorId(), postDto.getContent());
    }
}