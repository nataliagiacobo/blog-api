package br.com.blog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PostRequest {
    @NotBlank(message = "Invalid Content: Empty content")
    @NotNull(message = "Invalid Content: Content is NULL")
    private String content;
    @NotNull(message = "Invalid UserId: UserId is NULL")
    private Long userId;
}
