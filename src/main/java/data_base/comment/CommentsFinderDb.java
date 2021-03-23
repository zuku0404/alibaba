package data_base.comment;

import data_base.ConnectorDB;
import model.dao.Comment;
import model.dao.Post;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class CommentsFinderDb {
    public List<Comment> findAllCommentByPost(Post post) {
        List<Comment> comments = new LinkedList<>();
        String query = "Select * from Comment where post_id = ?";
        PreparedStatement preparedStatement;
        try (Connection connection = ConnectorDB.createConnection()) {
            preparedStatement =  connection.prepareStatement(query);
            preparedStatement.setLong(1,post.getId());
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

    public Optional<Comment> findByDate(LocalDateTime created)  {
            String query = "Select * from Comment where created = ?";
            PreparedStatement preparedStatement;
            try (Connection connection = ConnectorDB.createConnection()) {
                preparedStatement =  connection.prepareStatement(query);
                preparedStatement.setTimestamp(1,Timestamp.valueOf(created));
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
}

