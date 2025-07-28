package com.alumverse.alumverse.dto;

import java.time.LocalDateTime;

public class PostDto {
    private String id;
    private String authorId;
    private String content;
    private LocalDateTime timestamp;
    private int likesCount; // To represent the number of likes, not the list

    // Constructors
    public PostDto() {}

    public PostDto(String id, String authorId, String content, LocalDateTime timestamp, int likesCount) {
        this.id = id;
        this.authorId = authorId;
        this.content = content;
        this.timestamp = timestamp;
        this.likesCount = likesCount;
    }

    // Getters and Setters
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

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }
}