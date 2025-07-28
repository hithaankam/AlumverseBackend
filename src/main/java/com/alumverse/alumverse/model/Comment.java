package com.alumverse.alumverse.model;

import java.time.LocalDateTime;

public class Comment {
    private String authorId;
    private String content;
    private LocalDateTime timestamp;

    // Constructors, Getters, and Setters
    public Comment() {}

    public Comment(String authorId, String content) {
        this.authorId = authorId;
        this.content = content;
        this.timestamp = LocalDateTime.now();
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
}