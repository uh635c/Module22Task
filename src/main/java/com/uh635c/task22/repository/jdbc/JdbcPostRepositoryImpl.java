package com.uh635c.task22.repository.jdbc;

import com.uh635c.task22.model.*;
import com.uh635c.task22.repository.*;

import java.sql.*;
import java.util.*;

public class JdbcPostRepositoryImpl implements PostRepository {

    private Post getPostFromResultSet(ResultSet resultSet) throws SQLException {
        Post post = new Post();
        post.setId(resultSet.getLong("id"));
        post.setContent(resultSet.getString("content"));

        if (resultSet.getString("tag_status").equals("ACTIVE")) {
            post.setStatus(PostStatus.ACTIVE);
        } else {
            post.setStatus(PostStatus.DELETED);
        }
        return post;
    }

    @Override
    public List<Post> getAll() {
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT * FROM Posts";

        try (PreparedStatement statement = ConnectionDB.getPreparedStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                posts.add(getPostFromResultSet(resultSet));
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return posts;
    }

    @Override
    public Post getById(Long id) {
        Post post = null;
        List<Tag> tags = new ArrayList<>();
        String sql = "Select p.id AS id, p.content AS content, p.tag_status, t.id AS tag_id, t.name AS tag_name, p.writer_id AS writer_id " +
                "FROM posts AS p JOIN posts_tags AS ps ON p.id=ps.post_id " +
                "JOIN tags AS t ON ps.tag_id=t.id " +
                "WHERE p.id = ?";

        try (PreparedStatement preparedStatement = ConnectionDB.getPreparedStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            post = getPostFromResultSet(resultSet);

            Writer writer = new Writer();
            writer.setId(resultSet.getLong("writer_id"));
            post.setWriter(writer);

            do {
                Tag tag = new Tag();
                tag.setName(resultSet.getString("tag_name"));
                tag.setId(resultSet.getLong("tag_id"));
                tags.add(tag);
            } while (resultSet.next());

            post.setTags(tags);

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return post;
    }

    @Override
    public Post save(Post post) {
        String sql = "INSERT INTO posts (id, content, writer_id, tag_status) VALUES (?,?,?,?)";

        try{
            PreparedStatement preparedStatement = ConnectionDB.getPreparedStatement(sql);

            //filling post table
            preparedStatement.setLong(1, post.getId());
            preparedStatement.setString(2, post.getContent());
            preparedStatement.setLong(3, post.getWriter().getId());
            preparedStatement.setString(4, post.getStatus().toString());
            preparedStatement.executeUpdate();
            preparedStatement.close();

            //filling posts-tags table
            sql = "INSERT INTO posts_tags (post_id, tag_id) VALUES (?,?)";
            preparedStatement = ConnectionDB.getPreparedStatement(sql);
            preparedStatement.setLong(1,post.getId());

            for(Tag tag : post.getTags()){
                preparedStatement.setLong(2, tag.getId());
                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();
            preparedStatement.close();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return post;
    }

    @Override
    public Post update(Post post) {
        String sql = "UPDATE posts SET content = ?, writer_id = ?, tag_status = ? WHERE id = ?";

        try{
            PreparedStatement preparedStatement = ConnectionDB.getPreparedStatement(sql);

            //filling post table
            preparedStatement.setLong(4, post.getId());
            preparedStatement.setString(1, post.getContent());
            preparedStatement.setLong(2, post.getWriter().getId());
            preparedStatement.setString(3, post.getStatus().toString());
            preparedStatement.executeUpdate();
            preparedStatement.close();

            //deleting rows in the table posts-tags where id is the id of the updated post
            preparedStatement = ConnectionDB.getPreparedStatement(
                    "DELETE FROM posts_tags WHERE post_id = ?");
            preparedStatement.setLong(1, post.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();

            //filling posts-tags table
            sql = "INSERT INTO posts_tags (post_id, tag_id) VALUES (?,?)";
            preparedStatement = ConnectionDB.getPreparedStatement(sql);
            preparedStatement.setLong(1,post.getId());

            for(Tag tag : post.getTags()){
                preparedStatement.setLong(2, tag.getId());
                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();
            preparedStatement.close();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return post;
    }

    @Override
    public void remove(Long id) {
        String sql = "DELETE FROM posts WHERE id = ?";

        try(PreparedStatement preparedStatement = ConnectionDB.getPreparedStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}
