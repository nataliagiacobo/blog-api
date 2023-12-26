package br.com.blog.dto;

import lombok.Data;

import java.util.List;

@Data
public class PostResponse {
    private Long id;
    private String content;
    private UserResponse user;
    private List<CommentResponse> comments;
}
