package br.com.blog.controller;

import br.com.blog.dto.PostRequest;
import br.com.blog.dto.PostResponse;
import br.com.blog.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService service;

    @PostMapping
    public ResponseEntity<PostResponse> savePost(@Valid @RequestBody PostRequest request) {
        PostResponse post =  service.savePost(request);
        return ResponseEntity.created(URI.create("/post/"+post.getId())).body(post);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPost(@PathVariable Long id){
        return ResponseEntity.ok(service.getPostById(id));
    }

    @GetMapping()
    public ResponseEntity<List<PostResponse>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        service.deletePost(id);
    }

}
