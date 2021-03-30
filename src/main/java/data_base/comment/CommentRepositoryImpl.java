package data_base.comment;

import data_base.ConnectorDB;
import model.dao.Comment;
import model.dao.Post;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class CommentRepositoryImpl implements CommentRepository {
    @Override
    public Comment saveComment(Comment comment) {
        String query = "INSERT INTO comment (user_id, post_id, content, created) VALUES (?,?,?,?)";
        PreparedStatement preparedStatement;
        try (Connection connection = ConnectorDB.createConnection()) {
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, comment.getUserId());
            preparedStatement.setLong(2, comment.getPostId());
            preparedStatement.setString(3, comment.getContent());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(comment.getCreated()));
            preparedStatement.addBatch();
            preparedStatement.executeBatch();
            preparedStatement.getGeneratedKeys();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return comment;
    }

    @Override
    public Comment updateComment(Comment comment, String content) {
        String query = "UPDATE comment SET content = ? WHERE ID = ?";
        PreparedStatement preparedStatement = null;
        try (Connection connection = ConnectorDB.createConnection()) {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, content);
            preparedStatement.setLong(2, comment.getId());
            preparedStatement.addBatch();
            preparedStatement.executeBatch();
            preparedStatement.getGeneratedKeys();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return comment;
    }

    @Override
    public List<Comment> findAllCommentByPost(Post post) {
        List<Comment> comments = new LinkedList<>();
        String query = "Select * from Comment where post_id = ?";
        PreparedStatement preparedStatement;
        try (Connection connection = ConnectorDB.createConnection()) {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, post.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                comments.add(Comment.builder()
                        .id(resultSet.getLong("ID"))
                        .postId(resultSet.getLong("POST_ID"))
                        .userId(resultSet.getLong("USER_ID"))
                        .content(resultSet.getString("CONTENT"))
                        .created(resultSet.getTimestamp("CREATED").toLocalDateTime())
                        .build());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return comments;
    }

    @Override
    public Optional<Comment> findByDate(LocalDateTime created) {
        String query = "Select * from Comment where created = ?";
        PreparedStatement preparedStatement;
        try (Connection connection = ConnectorDB.createConnection()) {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setTimestamp(1, Timestamp.valueOf(created));
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return Optional.of(Comment.builder()
                    .id(resultSet.getLong("ID"))
                    .postId(resultSet.getLong("POST_ID"))
                    .userId(resultSet.getLong("USER_ID"))
                    .content(resultSet.getString("CONTENT"))
                    .created(resultSet.getTimestamp("CREATED").toLocalDateTime())
                    .build());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void removeComment(Comment comment) {
        String query = "DELETE FROM comment WHERE id = ?";
        PreparedStatement preparedStatement;
        try (Connection connection = ConnectorDB.createConnection()) {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, comment.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

