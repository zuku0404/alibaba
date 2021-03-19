package data_base.post;

import data_base.ConnectorDB;
import model.Post;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PostFinderDb {
    public List<Post> findAllPost() {
        List<Post> posts = new ArrayList<>();
        String query = "Select * from Post";
        Statement statement = null;
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
            ResultSet resultSet = preparedStatement.executeQuery(query);
            resultSet.next();
            return Optional.of(Post.builder()
                    .id(resultSet.getLong("ID"))
                    .userId(resultSet.getLong("USER_ID"))
                    .title(resultSet.getString("TITLE"))
                    .content(resultSet.getString("CONTENT"))
                    .created(resultSet.getTimestamp("CREATED").toLocalDateTime())
                    .build());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Optional.empty();
    }
}