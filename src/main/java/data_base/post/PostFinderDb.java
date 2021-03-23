package data_base.post;

import data_base.ConnectorDB;
import model.dao.Post;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PostFinderDb {
    public List<Post> findAllPost() {
        List<Post> posts = new ArrayList<>();
        String query = "Select * from Post";
        Statement statement;
        try (Connection connection = ConnectorDB.createConnection()) {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                posts.add(Post.builder()
                        .id(resultSet.getLong("ID"))
                        .userId(resultSet.getLong("USER_ID"))
                        .title(resultSet.getString("TITLE"))
                        .content(resultSet.getString("CONTENT"))
                        .created(resultSet.getTimestamp("CREATED").toLocalDateTime())
                        .build());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return posts;
    }

    public Optional<Post> findByTitle(String title) {
        String query = "SELECT * FROM post WHERE title = ?";
        PreparedStatement preparedStatement = null;
        try (Connection connection = ConnectorDB.createConnection()) {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, title);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(Post.builder()
                        .id(resultSet.getLong("ID"))
                        .userId(resultSet.getLong("USER_ID"))
                        .title(resultSet.getString("TITLE"))
                        .content(resultSet.getString("CONTENT"))
                        .created(resultSet.getTimestamp("CREATED").toLocalDateTime())
                        .build());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Optional.empty();
    }
    public List<Post> findByUserId (long user_id) {
        List<Post> posts = new ArrayList<>();
        String query = "SELECT * FROM post WHERE user_id = ?";
        PreparedStatement preparedStatement;
        try (Connection connection = ConnectorDB.createConnection()) {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, user_id);
            ResultSet resultSet = preparedStatement.executeQuery(query);
            while (resultSet.next()) {
                posts.add(Post.builder()
                        .id(resultSet.getLong("ID"))
                        .userId(resultSet.getLong("USER_ID"))
                        .title(resultSet.getString("TITLE"))
                        .content(resultSet.getString("CONTENT"))
                        .created(resultSet.getTimestamp("CREATED").toLocalDateTime())
                        .build());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return posts;
    }
}
