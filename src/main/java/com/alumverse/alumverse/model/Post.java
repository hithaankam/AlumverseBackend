package com.alumverse.alumverse.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList; // Import ArrayList
import java.util.List;

@Document(collection = "posts")
public class Post {
    @Id
    private String id;

    private String authorId;
    private String content;
    private LocalDateTime timestamp;
    private List<String> likes = new ArrayList<>(); // Initialize the list here
    private List<Comment> comments = new ArrayList<>(); // Initialize the list here

    // Constructors, Getters, and Setters
    public Post() {}

    public Post(String authorId, String content) {
        this.authorId = authorId;
        this.content = content;
        this.timestamp = LocalDateTime.now();
        // You don't need to initialize them again here if you did it above
    }

    // Getters and Setters for all fields
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public List<String> getLikes() {
        return likes;
    }

    public void setLikes(List<String> likes) {
        this.likes = likes;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}