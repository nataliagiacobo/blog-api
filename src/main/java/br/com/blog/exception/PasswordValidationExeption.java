package br.com.blog.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PasswordValidationExeption extends Exception{
    private String message;
}
