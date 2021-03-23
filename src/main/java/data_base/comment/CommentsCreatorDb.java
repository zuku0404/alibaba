package data_base.comment;

import data_base.ConnectorDB;
import model.dao.Comment;

import java.sql.*;

public class CommentsCreatorDb {
    public Comment saveComment (Comment comment){
        String query = "INSERT INTO comment (user_id, post_id, content, created) VALUES (?,?,?,?)";
        PreparedStatement preparedStatement;
        try (Connection connection = ConnectorDB.createConnection()){
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1,comment.getUserId());
            preparedStatement.setLong(2,comment.getPostId());
            preparedStatement.setString(3,comment.getContent());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(comment.getCreated()));
            preparedStatement.addBatch();
            preparedStatement.executeBatch();
            preparedStatement.getGeneratedKeys();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return comment;
    }
}

