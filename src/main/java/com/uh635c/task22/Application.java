package com.uh635c.task22;

import com.uh635c.task22.controller.TagController;
import com.uh635c.task22.model.Post;
import com.uh635c.task22.model.PostStatus;
import com.uh635c.task22.model.Tag;
import com.uh635c.task22.model.Writer;
import com.uh635c.task22.repository.*;
import com.uh635c.task22.view.MainView;
import com.uh635c.task22.view.TagView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Long.parseLong;

public class Application {

    public static void main(String[] args) {

//        MainView mainView = new MainView();
//        mainView.showStartApplication();

        List<Tag> tags = new ArrayList<>();



//        TagRepository tagRepository = new TagRepositoryImpl();
//        PostRepository postRepository = new PostRepositoryImpl();
//        WriterRepository writerRepository = new WriterRepositoryImpl();
//
//        System.out.println(writerRepository.getById(2L));
//
//        Writer writer = new Writer();
//        writer.setId(4L);
//        writer.setName("writer4");
//        writerRepository.save(writer);

//        writer.setName("writer4 updated");
//        writerRepository.update(writer);

//        writerRepository.remove(4L);

//        System.out.println(postRepository.getAll());
//        System.out.println(postRepository.getById(1L));
//
//        postRepository.remove(4L);

//        Post post = new Post();
//        post.setId(4L);
//        post.setContent("content 4 revised");
//        Tag tag1 = new Tag();
//        Tag tag2 = new Tag();
//        tag1.setId(2L);
//        tag1.setName("second");
//        tag2.setId(4L);
//        tag2.setName("forth");
//        Writer writer = new Writer();
//        writer.setId(1L);
//        writer.setName("writer1");
//        writer.setPosts(null);
//        post.setWriter(writer);
//        post.setStatus(PostStatus.ACTIVE);
//        post.setTags(Stream.of(tag1, tag2).collect(Collectors.toList()));
//        System.out.println(postRepository.update(post));

//        Post post = new Post();
//        post.setId(1L);
//        post.setContent("content 1");
//        Tag tag1 = new Tag();
//        Tag tag2 = new Tag();
//        tag1.setId(1L);
//        tag1.setName("first_updated");
//        tag2.setId(3L);
//        tag2.setName("third");
//        post.setTags(Stream.of(tag1, tag2).collect(Collectors.toList()));
//        Writer writer = new Writer();
//        writer.setId(1L);
//        writer.setName("writer1");
//        writer.setPosts(null);
//        post.setWriter(writer);
//        post.setStatus(PostStatus.ACTIVE);
//
//        postRepository.save(post);


//        __________________________________________

        //        Tag tag = new Tag();
//        tag.setId(1L);
//        tag.setName("first");
//        tagRepository.save(tag);

//        System.out.println(tagRepository.getAll());
//        System.out.println("--------------");

//        Tag tag = new Tag();
//        tag.setId(1L);
//        tag.setName("first_updated");
//        tagRepository.update(tag);
//        System.out.println(tagRepository.getAll());
//        System.out.println("--------------");

//        tagRepository.remove(3L);
//        System.out.println(tagRepository.getAll());
//        System.out.println("--------------");

//        System.out.println(tagRepository.getById(1L));

    }
}
