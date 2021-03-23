package data_base.comment;

import data_base.ConnectorDB;
import model.dao.Comment;
import model.dao.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CommentsRemoverDb {
    public void deleteComment(Comment comment) {
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
