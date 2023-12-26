package br.com.blog.service;

import br.com.blog.model.Album;
import br.com.blog.model.Image;
import br.com.blog.repository.AlbumRepository;
import br.com.blog.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.NoSuchElementException;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private AlbumRepository albumRepository;

    public Long uploadImage(MultipartFile file, Long albumId) throws IOException {
        Album album = null;

        if (albumId != null)
            album = albumRepository.findById(albumId)
                .orElseThrow(() -> new NoSuchElementException("Album not found"));

        Image image = new Image();
        image.setName(file.getOriginalFilename());
        image.setType(file.getContentType());
        image.setImage(file.getBytes());
        image.setAlbum(album);
        image = imageRepository.save(image);

        return image.getId();
    }

    public byte[] getImageById(Long id) {
        Image image = imageRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Image not found"));
        return image.getImage();
    }
}
