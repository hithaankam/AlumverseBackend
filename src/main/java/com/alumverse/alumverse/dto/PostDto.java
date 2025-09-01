package com.alumverse.alumverse.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class PostDto {
    private String id;
    private String authorId;
    private String content;
    private LocalDateTime timestamp;
    private int likesCount; //count


}