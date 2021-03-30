package data_base.post;

import data_base.ConnectorDB;
import model.dao.Post;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PostRepositoryImpl implements PostRepository {
    @Override
    public Post savePost(Post post) {
        String query = "INSERT INTO post (user_id, title, content, created) VALUES (?,?,?,?)";
        PreparedStatement preparedStatement;
        try (Connection connection = ConnectorDB.createConnection()) {
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, post.getUserId());
            preparedStatement.setString(2, post.getTitle());
            preparedStatement.setString(3, post.getContent());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(post.getCreated()));
            preparedStatement.addBatch();
            preparedStatement.executeBatch();
            preparedStatement.getGeneratedKeys();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return post;
    }

    @Override
    public Post updatePost(Post post, String editedTitle, String editedContent) {
        String query = "UPDATE post SET title = ?, content = ? WHERE ID = ?";
        PreparedStatement preparedStatement;
        try (Connection connection = ConnectorDB.createConnection()) {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, editedTitle);
            preparedStatement.setString(2, editedContent);
            preparedStatement.setLong(3, post.getId());
            preparedStatement.addBatch();
            preparedStatement.executeBatch();
            preparedStatement.getGeneratedKeys();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return post;

    }

    @Override
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

    @Override
    public void removePost(Post post) {
        String query = "DELETE FROM post WHERE id = ?";
        PreparedStatement preparedStatement = null;
        try (Connection connection = ConnectorDB.createConnection()) {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, post.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
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

    @Override
    public List<Post> findByUserId(Long userId) {
        List<Post> posts = new ArrayList<>();
        String query = "SELECT * FROM post WHERE user_id = ?";
        PreparedStatement preparedStatement;
        try (Connection connection = ConnectorDB.createConnection()) {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, userId);
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
