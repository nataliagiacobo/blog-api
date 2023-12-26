package br.com.blog.service;

import br.com.blog.dto.PostRequest;
import br.com.blog.dto.PostResponse;
import br.com.blog.model.Post;
import br.com.blog.model.User;
import br.com.blog.repository.PostRepository;
import br.com.blog.repository.UserRepository;
import br.com.blog.utils.PostConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public PostResponse savePost(PostRequest request)  {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        Post post = PostConvert.toEntity(request, user);
        post.setActive(true);
        post = postRepository.save(post);
        return PostConvert.toResponse(post);
    }

    public PostResponse getPostById(Long id){
        return PostConvert.toResponse(postRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Post not found")));
    }

    public Page<PostResponse> getPosts(int page, int size, String direction){
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.fromString(direction), "id");
        Page<Post> posts = postRepository.findAll(pageRequest);
        return PostConvert.toResponsePage(posts);
    }

    public List<PostResponse> getAll(){
        return PostConvert.toResponseList(postRepository.findAllByOrderByIdDesc());
    }

    public void deletePost(Long id){
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Post not found"));
        post.setActive(false);
        postRepository.save(post);
    }
}
