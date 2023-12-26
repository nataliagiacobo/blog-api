package br.com.blog.service;

import br.com.blog.dto.UserRequest;
import br.com.blog.dto.UserResponse;
import br.com.blog.exception.PasswordValidationExeption;
import br.com.blog.model.User;
import br.com.blog.repository.UserRepository;
import br.com.blog.utils.UserConvert;
import br.com.blog.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserResponse saveUser(UserRequest userDTO) throws Exception {
        if(repository.findByUsername(userDTO.getUsername()) != null)
            throw new IllegalArgumentException("User already exists");

        User user = UserConvert.toEntity(userDTO);

        if (!Validator.passwordValidate(userDTO.getPassword()))
            throw new PasswordValidationExeption("Invalid password");

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        User userEntity = repository.save(user);
        return UserConvert.toResponse(userEntity);
    }

    public UserResponse getUserById(Long id){
        return UserConvert.toResponse(repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found")));
    }
}
