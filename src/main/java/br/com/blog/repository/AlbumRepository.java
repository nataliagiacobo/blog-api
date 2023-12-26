package br.com.blog.repository;

import br.com.blog.model.Album;
import br.com.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AlbumRepository extends JpaRepository<Album, Long> {
    Optional<Album> findByNameAndUser(String name, User user);

    List<Album> findAllByOrderByIdDesc();
}
