package br.com.blog.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AlbumResponse {
    private Long id;
    private String name;
    private String description;
    private UserResponse user;
    private List<String> photos;
}
