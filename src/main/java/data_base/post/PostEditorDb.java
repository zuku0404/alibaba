package data_base.post;

import data_base.ConnectorDB;
import model.dao.Post;

import java.sql.*;

public class PostEditorDb {
    public Post updatePost(Post post, String editedTitle, String editedContent) {
        String query = "UPDATE post SET title = ?, content = ? WHERE ID = ?";
        PreparedStatement preparedStatement ;
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
}
