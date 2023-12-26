package br.com.blog.controller;


import br.com.blog.repository.ImageRepository;
import br.com.blog.service.ImageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/image")
public class ImageController {
    @Autowired
    private ImageService service;

    @Autowired
    private ImageRepository imagemRepository;


    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("upload") MultipartFile file) {
        try {
            Long id =  service.uploadImage(file, null);

            String imageUrl = "http://localhost:8080/image/" + id; // URL da imagem

            Map<String, String> response = new HashMap<>();
            response.put("url", imageUrl);

            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar a imagem: " + e.getMessage());
        }
    }

    @PostMapping("/upload/album/{albumId}")
    public ResponseEntity<?> addImage(@RequestParam("upload") MultipartFile file, @PathVariable Long albumId) {
        try {
            Long id =  service.uploadImage(file, albumId);

            String imageUrl = "http://localhost:8080/image/" + id; // URL da imagem

            Map<String, String> response = new HashMap<>();
            response.put("url", imageUrl);

            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar a imagem: " + e.getMessage());
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getImageById(@PathVariable Long id){
        byte[] image = service.getImageById(id);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(image);
    }
}
