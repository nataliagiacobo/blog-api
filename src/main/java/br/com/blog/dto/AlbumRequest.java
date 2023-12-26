package br.com.blog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AlbumRequest {
    @NotBlank(message = "Invalid Name: Empty name")
    @NotNull(message = "Invalid Name: Name is NULL")
    @Size(min = 3, max = 30, message = "Invalid Name: Must be of 3 - 30 characters")
    private String name;
    private String description;
    @NotNull(message = "Invalid UserId: UserId is NULL")
    private Long userId;
}
