package br.com.blog.utils;

import br.com.blog.dto.ImageResponse;
import br.com.blog.model.Image;

import java.util.ArrayList;
import java.util.List;

public class ImageConvert {
    public static ImageResponse toResponse(Image image){
        ImageResponse imageResponse = new ImageResponse();
        imageResponse.setId(image.getId());
        imageResponse.setName(image.getName());
        imageResponse.setType(image.getType());
        imageResponse.setImage(image.getImage());
        return imageResponse;
    }
    public static List<ImageResponse> toResponseList(List<Image> imageList){
        List<ImageResponse> imageResponseList = new ArrayList<>();
        for (Image image : imageList) {
            ImageResponse imageResponse = ImageConvert.toResponse(image);
            imageResponseList.add(imageResponse);
        }
        return imageResponseList;
    }

    public static List<String> getUrlList(List<Image> imageList){
        List<String> imageResponseList = new ArrayList<>();
        for (Image image : imageList) {
            imageResponseList.add("http://localhost:8080/image/" + image.getId());
        }
        return imageResponseList;
    }
}
