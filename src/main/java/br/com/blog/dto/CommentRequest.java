package br.com.blog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CommentRequest {
    @NotBlank(message = "Invalid description: Empty description")
    @NotNull(message = "Invalid description: description is NULL")
    private String description;
    @NotNull(message = "Invalid PostId: PostId is NULL")
    private Long postId;
    @NotNull(message = "Invalid UserId: UserId is NULL")
    private Long userId;
}
