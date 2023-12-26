package br.com.blog.dto;

import lombok.Data;

@Data
public class CommentResponse {
    private Long id;
    private String description;
    private UserResponse user;
}
