package data_base.comment;

import data_base.ConnectorDB;
import model.Comment;
import model.Post;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

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
}

