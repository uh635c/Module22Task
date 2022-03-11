package com.uh635c.task22.model;

import java.util.List;
import java.util.Objects;

public class Post {
    private Long id;
    private String content;
    private List<Tag> tags;
    private PostStatus status;
    private Writer writer;

    public Post() {
    }

    public Post(Long id, String content, List<Tag> tags, PostStatus status, Writer writer) {
        this.id = id;
        this.content = content;
        this.tags = tags;
        this.status = status;
        this.writer = writer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id) && Objects.equals(content, post.content) && Objects.equals(tags, post.tags)
                && status == post.status && Objects.equals(writer.getId(), post.writer.getId());
    }

    public Writer getWriter() {
        return writer;
    }

    public void setWriter(Writer writer) {
        this.writer = writer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public PostStatus getStatus() {
        return status;
    }

    public void setStatus(PostStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", tags=" + tags +
                ", status=" + status +
                ", writer_id=" + writer.getId() +
                '}';
    }
}
