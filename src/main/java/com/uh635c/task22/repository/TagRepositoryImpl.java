package com.uh635c.task22.repository;

import com.uh635c.task22.model.Tag;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TagRepositoryImpl implements TagRepository {

    @Override
    public List<Tag> getAll() {
        List<Tag> tags = new ArrayList<>();
        String sql = "SELECT * FROM tags";

        try(Statement statement = ConnectionDB.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql)) {

            while(resultSet.next()){
                Tag tag = new Tag();
                tag.setId(resultSet.getLong("id"));
                tag.setName(resultSet.getString("name"));
                tags.add(tag);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return tags;
    }

    @Override
    public Tag getById(Long id) {
        Tag tag = new Tag();
        String sql = "SELECT name FROM tags WHERE id = ?";

        try (PreparedStatement preparedStatement = ConnectionDB.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                tag.setId(id);
                tag.setName(resultSet.getString("name"));
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return tag;
    }

    @Override
    public Tag save(Tag tag) {
        String sql = "INSERT INTO tags (id, name) VALUES (?,?)";

        try (PreparedStatement preparedStatement = ConnectionDB.getConnection().prepareStatement(sql)) {
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

        try (PreparedStatement preparedStatement = ConnectionDB.getConnection().prepareStatement(sql)) {
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

        try (PreparedStatement preparedStatement = ConnectionDB.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}
