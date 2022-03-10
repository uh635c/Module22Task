package com.uh635c.task22.service;

import com.uh635c.task22.model.Tag;
import com.uh635c.task22.repository.TagRepository;
import com.uh635c.task22.repository.TagRepositoryImpl;

import java.util.List;

public class TagService {
    private TagRepository tagRepository = new TagRepositoryImpl();

    public List<Tag> getAll(){
        return tagRepository.getAll();
    }

    public Tag getById(Long id){
        return tagRepository.getById(id);
    }

    public Tag save(Tag tag){
        return tagRepository.save(tag);
    }

    public Tag update(Tag tag){
        return tagRepository.update(tag);
    }

    public void remove (Long id){
        tagRepository.remove(id);
    }

}
