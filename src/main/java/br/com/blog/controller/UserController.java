package br.com.blog.controller;

import br.com.blog.dto.UserRequest;
import br.com.blog.dto.UserResponse;
import br.com.blog.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<UserResponse> saveUser(@Valid @RequestBody UserRequest request) throws Exception {
        UserResponse user =  service.saveUser (request);
        return ResponseEntity.created(URI.create("/user/"+user.getId())).body(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id){
        return ResponseEntity.ok(service.getUserById(id));
    }
}
