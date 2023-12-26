package br.com.blog.utils;

import br.com.blog.dto.AlbumRequest;
import br.com.blog.dto.AlbumResponse;
import br.com.blog.model.Album;
import br.com.blog.model.User;

import java.util.ArrayList;
import java.util.List;

public class AlbumConvert {
    public static Album toEntity(AlbumRequest albumRequest, User user){
        Album album = new Album();
        album.setName(albumRequest.getName());
        album.setDescription(albumRequest.getDescription());
        album.setUser(user);
        return album;
    }

    public static AlbumResponse toResponse(Album album){
        AlbumResponse albumResponse = new AlbumResponse();
        albumResponse.setId(album.getId());
        albumResponse.setName(album.getName());
        albumResponse.setDescription(album.getDescription());
        albumResponse.setUser(UserConvert.toResponse(album.getUser()));
        if (album.getPhotos() != null && !album.getPhotos().isEmpty())
            albumResponse.setPhotos(ImageConvert.getUrlList(album.getPhotos()));
        return albumResponse;
    }

    public static List<AlbumResponse> toResponseList(List<Album> albums){
        List<AlbumResponse> albumResponseList = new ArrayList<>();
        for (Album album : albums) {
            AlbumResponse albumResponse = toResponse(album);
            albumResponseList.add(albumResponse);
        }
        return albumResponseList;
    }
}
