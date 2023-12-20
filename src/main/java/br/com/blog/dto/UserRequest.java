package br.com.blog.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserRequest {
    @NotBlank
    @Length(min = 5)
    private String name;
    @Email
    private String email;
    @NotNull(message = "Username cannot be blank")
    @NotBlank(message = "Username cannot be null")
    private String password;
}
