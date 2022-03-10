package com.uh635c.task22.service;

import com.uh635c.task22.model.Post;
import com.uh635c.task22.repository.PostRepository;
import com.uh635c.task22.repository.PostRepositoryImpl;

import java.util.List;

public class PostService {
    private PostRepository postRepository = new PostRepositoryImpl();

    public List<Post> getAll(){
        return postRepository.getAll();
    }

    public Post getById(Long id){
        return postRepository.getById(id);
    }

    public Post save(Post post){
        return postRepository.save(post);
    }

    public Post update(Post post){
        return postRepository.update(post);
    }

    public void remove(Long id){
        postRepository.remove(id);
    }

}
