package com.uh635c.task22.repository.jdbc;

import com.uh635c.task22.model.Tag;
import com.uh635c.task22.repository.ConnectionDB;
import com.uh635c.task22.repository.TagRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcTagRepositoryImpl implements TagRepository {

    private Tag getTagFromResultSet(ResultSet resultSet) throws SQLException {
        Tag tag = new Tag();
        tag.setId(resultSet.getLong("id"));
        tag.setName(resultSet.getString("name"));
        return tag;
    }

    @Override
    public List<Tag> getAll() {
        List<Tag> tags = new ArrayList<>();
        String sql = "SELECT * FROM tags";

        try(PreparedStatement statement = ConnectionDB.getPreparedStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {

            while(resultSet.next()){
                tags.add(getTagFromResultSet(resultSet));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return tags;
    }

    @Override
    public Tag getById(Long id) {
        Tag tag = null;
        String sql = "SELECT * FROM tags WHERE id = ?";

        try (PreparedStatement preparedStatement = ConnectionDB.getPreparedStatement(sql)) {
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                tag =getTagFromResultSet(resultSet);
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return tag;
    }

    @Override
    public Tag save(Tag tag) {
        String sql = "INSERT INTO tags (id, name) VALUES (?,?)";

        try (PreparedStatement preparedStatement = ConnectionDB.getPreparedStatement(sql)) {
            preparedStatement.setLong(1, tag.getId());
            preparedStatement.setString(2, tag.getName());
            preparedStatement.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return tag;
    }

    @Override
    public Tag update(Tag tag) {
        String sql = "UPDATE tags SET name = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = ConnectionDB.getPreparedStatement(sql)) {
            preparedStatement.setLong(2, tag.getId());
            preparedStatement.setString(1, tag.getName());
            preparedStatement.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return tag;
    }

    @Override
    public void remove(Long id) {
        String sql = "DELETE FROM tags WHERE id = ?";

        try (PreparedStatement preparedStatement = ConnectionDB.getPreparedStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}
