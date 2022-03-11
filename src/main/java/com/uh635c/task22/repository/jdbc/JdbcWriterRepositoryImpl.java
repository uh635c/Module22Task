package com.uh635c.task22.repository.jdbc;

import com.uh635c.task22.model.Post;
import com.uh635c.task22.model.Writer;
import com.uh635c.task22.repository.ConnectionDB;
import com.uh635c.task22.repository.PostRepository;
import com.uh635c.task22.repository.WriterRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcWriterRepositoryImpl implements WriterRepository {

    private PostRepository postRepository = new JdbcPostRepositoryImpl();

    private Writer getWriterFromResultSet(ResultSet resultSet) throws SQLException {
        Writer writer = new Writer();
        writer.setId(resultSet.getLong("id"));
        writer.setName(resultSet.getString("name"));
        return writer;
    }

    @Override
    public List<Writer> getAll() {
        List<Writer> writers = new ArrayList<>();

        String sql = "SELECT * FROM writers";

        try(PreparedStatement preparedStatement = ConnectionDB.getPreparedStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                writers.add(getWriterFromResultSet(resultSet));
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return writers;
    }

    @Override
    public Writer getById(Long id) {
        Writer writer = null;
        List<Post> posts = new ArrayList<>();

        String sql = "SELECT w.id AS id, w.name AS name, p.id AS post_id " +
                "FROM writers AS w JOIN posts AS p ON w.id=p.writer_id " +
                "WHERE w.id = ?";

        try (PreparedStatement preparedStatement = ConnectionDB.getPreparedStatement(sql)){
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            writer = getWriterFromResultSet(resultSet);

            do{
                Post post = postRepository.getById(resultSet.getLong("post_id"));
                posts.add(post);

            }while (resultSet.next());

            writer.setPosts(posts);

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return writer;
    }

    @Override
    public Writer save(Writer writer) {
        String sql = "INSERT INTO writers (id, name) VALUES (?,?)";

        try (PreparedStatement preparedStatement = ConnectionDB.getPreparedStatement(sql);) {
            preparedStatement.setLong(1, writer.getId());
            preparedStatement.setString(2, writer.getName());
            preparedStatement.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();

        }

        return writer;
    }

    @Override
    public Writer update(Writer writer) {
        String sql = "UPDATE writers SET name = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = ConnectionDB.getPreparedStatement(sql)){
            preparedStatement.setString(1, writer.getName());
            preparedStatement.setLong(2, writer.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return writer;
    }

    @Override
    public void remove(Long id) {
        String sql = "DELETE FROM writers WHERE id = ?";

        try (PreparedStatement preparedStatement = ConnectionDB.getPreparedStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }


}
