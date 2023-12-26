package br.com.blog.utils;

import br.com.blog.dto.CommentRequest;
import br.com.blog.dto.CommentResponse;
import br.com.blog.model.Comment;
import br.com.blog.model.Post;
import br.com.blog.model.User;

import java.util.ArrayList;
import java.util.List;

public class CommentConvert {
    public static Comment toEntity(CommentRequest commentRequest, User user, Post post){
        Comment comment = new Comment();
        comment.setDescription(commentRequest.getDescription());
        comment.setPost(post);
        comment.setUser(user);
        return comment;
    }

    public static CommentResponse toResponse(Comment comment){
        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setId(comment.getId());
        commentResponse.setDescription(comment.getDescription());
        commentResponse.setUser(UserConvert.toResponse(comment.getUser()));
        return commentResponse;
    }
    public static List<CommentResponse> toResponseList(List<Comment> comments){
        List<CommentResponse> commentResponseList = new ArrayList<>();
        for (Comment comment : comments) {
            CommentResponse commentResponse = CommentConvert.toResponse(comment);
            commentResponseList.add(commentResponse);
        }
        return commentResponseList;
    }
}
