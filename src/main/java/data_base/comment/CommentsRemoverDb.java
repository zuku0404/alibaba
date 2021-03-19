package data_base.comment;

import data_base.ConnectorDB;
import model.Comment;
import model.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CommentsRemoverDb {
    public void deleteComment(Comment comm) {
        String query = "DELETE FROM comment WHERE id = ?";
        PreparedStatement preparedStatement = null;
        try (Connection connection = ConnectorDB.createConnection()) {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, comm.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void deleteCommentToPost (Post post) {
        String query = "DELETE FROM comment WHERE post_id = ?";
        PreparedStatement preparedStatement = null;
        try (Connection connection = ConnectorDB.createConnection()) {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, post.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
