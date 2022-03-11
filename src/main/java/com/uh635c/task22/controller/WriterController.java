package com.uh635c.task22.controller;

import com.uh635c.task22.model.Post;
import com.uh635c.task22.model.Writer;
import com.uh635c.task22.repository.jdbc.JdbcWriterRepositoryImpl;
import com.uh635c.task22.service.WriterService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import static java.lang.Long.parseLong;

public class WriterController {
    WriterService writerService = new WriterService(new JdbcWriterRepositoryImpl());

    private Long getMaxId(){
        Writer writer = writerService.getAll().stream().max(Comparator.comparing(Writer::getId)).orElse(null);
        return Objects.nonNull(writer) ? writer.getId() + 1 : 1;
    }

    private List<Post> createListOfPosts(String strPostIds) {
        List<Post> list = new ArrayList<>();
        PostController postController = new PostController();

        for (String str : strPostIds.split(", ")) {
            list.add(postController.getAll().stream().filter(a->a.getId()==parseLong(str)).findFirst().orElse(null));
        }
        return list;
    }

    public List<Writer> getAll(){
        return writerService.getAll();
    }

    public Writer getById(String id){
        return writerService.getById(parseLong(id));
    }

    public Writer save(String name){
        Writer writer = new Writer();
        writer.setId(getMaxId());
        writer.setName(name);

        return writerService.save(writer);
    }

    public Writer update(String id, String name){
        Writer writer = new Writer();
        writer.setId(parseLong(id));
        writer.setName(name);

        return writerService.update(writer);
    }

    public void remove(String id){
        writerService.remove(parseLong(id));
    }

}
