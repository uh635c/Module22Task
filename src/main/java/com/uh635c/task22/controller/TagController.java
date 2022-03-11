package com.uh635c.task22.controller;

import com.uh635c.task22.model.Tag;
import com.uh635c.task22.repository.jdbc.JdbcTagRepositoryImpl;
import com.uh635c.task22.service.TagService;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import static java.lang.Long.parseLong;

public class TagController {
    TagService tagService = new TagService(new JdbcTagRepositoryImpl());

    private Long getMaxId(){
        Tag tag = tagService.getAll().stream().max(Comparator.comparing(t -> t.getId())).orElse(null);
        return Objects.nonNull(tag) ? tag.getId() + 1 : 1;
    }

    public List<Tag> getAll(){
        return tagService.getAll();
    }

    public Tag getById(String id){
        return tagService.getById(parseLong(id));
    }

    public Tag save(String name){
        Tag tag = new Tag();
        tag.setId(getMaxId());
        tag.setName(name);
        return tagService.save(tag);
    }

    public Tag update(String id, String name){
        Tag tag = new Tag();
        tag.setId(parseLong(id));
        tag.setName(name);
        return tagService.update(tag);
    }

    public void remove(String id){
        tagService.remove(parseLong(id));
    }

}
