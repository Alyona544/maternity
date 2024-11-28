package com.example.maternityhome.service;

import com.example.maternityhome.model.Post;
import com.example.maternityhome.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    // Retrieve all posts.
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    // Save a new or updated post.
    public Post save(Post post) {
        return postRepository.save(post);
    }

    // Delete a post by ID.
    public void delete(Long id) {
        postRepository.deleteById(id);
    }

    // Find a specific post by ID.
    public Post findById(Long id) {
        return postRepository.findById(id).orElse(null);
    }
}