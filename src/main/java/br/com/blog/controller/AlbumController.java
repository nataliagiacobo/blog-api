package br.com.blog.controller;

import br.com.blog.dto.AlbumRequest;
import br.com.blog.dto.AlbumResponse;
import br.com.blog.service.AlbumService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    private AlbumService service;

    @PostMapping
    public ResponseEntity<AlbumResponse> saveAlbum(@Valid @RequestBody AlbumRequest request) throws Exception {
        AlbumResponse album = service.saveAlbum(request);
        return ResponseEntity.created(URI.create("/album/" + album.getId())).body(album);
    }

    @GetMapping("/{id}")
    ResponseEntity<AlbumResponse> getAlbum(@PathVariable Long id){
        return ResponseEntity.ok(service.getAlbumById(id));
    }

    @GetMapping()
    public ResponseEntity<List<AlbumResponse>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @DeleteMapping("/{id}")
    public void deleteAlbum(@PathVariable Long id) {
        service.deleteAlbum(id);
    }


}
