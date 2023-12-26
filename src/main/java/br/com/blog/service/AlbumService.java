package br.com.blog.service;

import br.com.blog.dto.AlbumRequest;
import br.com.blog.dto.AlbumResponse;
import br.com.blog.model.Album;
import br.com.blog.model.User;
import br.com.blog.repository.AlbumRepository;
import br.com.blog.repository.UserRepository;
import br.com.blog.utils.AlbumConvert;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AlbumService {
    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public AlbumResponse saveAlbum(AlbumRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        if (albumRepository.findByNameAndUser(request.getName(), user).isPresent())
            throw new IllegalArgumentException("Album already exists");

        Album album = AlbumConvert.toEntity(request, user);
        album.setActive(true);
        album = albumRepository.save(album);

        return AlbumConvert.toResponse(album);
    }

    public AlbumResponse getAlbumById(Long id){
        return AlbumConvert.toResponse(albumRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Album not found")));
    }

    @Transactional
    public List<AlbumResponse> getAll(){
        return AlbumConvert.toResponseList(albumRepository.findAllByOrderByIdDesc());
    }

    public void deleteAlbum(Long id){
        Album album = albumRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Album not found"));

        album.setActive(false);
        albumRepository.save(album);
    }

}
