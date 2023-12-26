package br.com.blog.utils;

import br.com.blog.dto.PostRequest;
import br.com.blog.dto.PostResponse;
import br.com.blog.model.Post;
import br.com.blog.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;

public class PostConvert {
    public static Post toEntity(PostRequest postRequest, User user){
        Post post = new Post();
        post.setContent(postRequest.getContent());
        post.setUser(user);
        return post;
    }

    public static PostResponse toResponse(Post post){
        PostResponse postResponse = new PostResponse();
        postResponse.setId(post.getId());
        postResponse.setContent(post.getContent());
        postResponse.setUser(UserConvert.toResponse(post.getUser()));
        if(post.getComments() != null && !post.getComments().isEmpty())
            postResponse.setComments(CommentConvert.toResponseList(post.getComments()));
        return postResponse;
    }

    public static Page<PostResponse> toResponsePage(Page<Post> posts){
        List<PostResponse> postResponseList = new ArrayList<>();
        for (Post post : posts) {
            PostResponse postResponse = toResponse(post);
            postResponseList.add(postResponse);
        }
        return new PageImpl<>(postResponseList);
    }

    public static List<PostResponse> toResponseList(List<Post> posts){
        List<PostResponse> postResponseList = new ArrayList<>();
        for (Post post : posts) {
            PostResponse postResponse = PostConvert.toResponse(post);
            postResponseList.add(postResponse);
        }
        return postResponseList;
    }
}
