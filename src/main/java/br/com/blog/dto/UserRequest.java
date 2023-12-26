package br.com.blog.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequest {
    @NotBlank(message = "Invalid Name: Empty name")
    @NotNull(message = "Invalid Name: Name is NULL")
    @Size(min = 3, max = 30, message = "Invalid Name: Must be of 3 - 30 characters")
    private String name;
    @NotBlank(message = "Invalid Username: Empty username")
    @NotNull(message = "Invalid Username: Username is NULL")
    @Size(min = 3, max = 30, message = "Invalid Username: Must be of 3 - 30 characters")
    private String username;
    @Email(message = "Invalid email")
    @NotNull(message = "Invalid email: Empty e-mail")
    @NotBlank(message = "Invalid email: E-mail is NULL")
    private String email;
    @NotNull(message = "Invalid password: Empty password")
    @NotBlank(message = "Invalid password: Password is NULL")
    private String password;
}
