package br.com.blog.service;

import br.com.blog.dto.CommentRequest;
import br.com.blog.dto.CommentResponse;
import br.com.blog.model.Comment;
import br.com.blog.model.Post;
import br.com.blog.model.User;
import br.com.blog.repository.CommentRepository;
import br.com.blog.repository.PostRepository;
import br.com.blog.repository.UserRepository;
import br.com.blog.utils.CommentConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    public CommentResponse saveComment(CommentRequest request)  {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        Post post = postRepository.findById(request.getPostId())
                .orElseThrow(() -> new NoSuchElementException("Post not found"));

        Comment comment = CommentConvert.toEntity(request, user, post);
        comment.setActive(true);
        comment = commentRepository.save(comment);
        return CommentConvert.toResponse(comment);
    }

    public CommentResponse getCommentById(Long id){
        return CommentConvert.toResponse(commentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Comment not found")));
    }

    public void deleteComment(Long id){
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Comment not found"));
        comment.setActive(false);
        commentRepository.save(comment);
    }
}
