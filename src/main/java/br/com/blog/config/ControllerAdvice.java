package br.com.blog.config;

import br.com.blog.exception.PasswordValidationExeption;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerAdvice {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public ApiError handler(Exception exception){
        return new ApiError(exception.getMessage(), new ArrayList<>());
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public ApiError handler(NoSuchElementException exception){
        return new ApiError(exception.getMessage(), new ArrayList<>());
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ApiError handler(IllegalArgumentException exception){
        return new ApiError(exception.getLocalizedMessage(), new ArrayList<>());
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiError handleValidationErrors(MethodArgumentNotValidException exception) {
        List<String> errors = exception.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        return new ApiError("Validation error", errors);
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PasswordValidationExeption.class)
    public ApiError handler(PasswordValidationExeption exception) {
        return new ApiError(exception.getMessage(), new ArrayList<>());
    }

    @Data
    @AllArgsConstructor
    public static class ApiError {
        private String message;
        private List<String> errors;
    }
}

