package data_base.comment;

import data_base.ConnectorDB;
import model.Comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CommentsEditorDB {
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
}

