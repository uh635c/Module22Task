package com.uh635c.task22.controller;

import com.uh635c.task22.model.Post;
import com.uh635c.task22.model.PostStatus;
import com.uh635c.task22.model.Tag;
import com.uh635c.task22.model.Writer;
import com.uh635c.task22.service.PostService;
import com.uh635c.task22.service.TagService;
import com.uh635c.task22.service.WriterService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import static java.lang.Long.parseLong;

public class PostController {
    PostService postService = new PostService();

    private Long getMaxId(){
        Post post = postService.getAll().stream().max(Comparator.comparing(Post::getId)).orElse(null);
        return Objects.nonNull(post) ? post.getId() + 1 : 1;
    }

    private List<Tag> createListOfTags(String strTagIds) {
        List<Tag> list = new ArrayList<>();
        TagController tagController = new TagController();

        for (String str : strTagIds.split(", ")) {
            list.add(tagController.getAll().stream().filter(a->a.getId()==parseLong(str)).findFirst().orElse(null));
        }
        return list;
    }

    public List<Post> getAll(){
        return postService.getAll();
    }

    public Post getById(String id){
        return postService.getById(parseLong(id));
    }

    public Post save(String content, String tagIds, String writerId, String status){
        Post post = new Post();
        post.setId(getMaxId());
        post.setContent(content);
        post.setTags(createListOfTags(tagIds));

        if (status.equals("ACTIVE")) {
            post.setStatus(PostStatus.ACTIVE);
        } else {
            post.setStatus(PostStatus.DELETED);
        }

        Writer writer = new Writer();
        writer.setId(parseLong(writerId));
        post.setWriter(writer);

        return postService.save(post);
    }

    public Post update(String id, String content, String tagIds, String writerId, String status){
        Post post = new Post();
        post.setId(parseLong(id));
        post.setContent(content);
        post.setTags(createListOfTags(tagIds));

        if (status.equals("ACTIVE")) {
            post.setStatus(PostStatus.ACTIVE);
        } else {
            post.setStatus(PostStatus.DELETED);
        }

        Writer writer = new Writer();
        writer.setId(parseLong(writerId));
        post.setWriter(writer);

        return postService.update(post);
    }

    public void remove(String id){
        postService.remove(parseLong(id));
    }
}
