package br.com.blog.controller;

import br.com.blog.dto.CommentRequest;
import br.com.blog.dto.CommentResponse;
import br.com.blog.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService service;

    @PostMapping
    public ResponseEntity<CommentResponse> saveComment(@Valid @RequestBody CommentRequest request) {
        CommentResponse comment =  service.saveComment(request);
        return ResponseEntity.created(URI.create("/comment/"+comment.getId())).body(comment);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentResponse> getComment(@PathVariable Long id){
        return ResponseEntity.ok(service.getCommentById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Long id) {
        service.deleteComment(id);
    }
}
