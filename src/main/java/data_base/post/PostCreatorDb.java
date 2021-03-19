package data_base.post;

import data_base.ConnectorDB;
import model.Post;

import java.sql.*;

public class PostCreatorDb {
    public Post savePost(Post post){
        String query = "INSERT INTO post (user_id, title, content, created) VALUES (?,?,?,?)";
        PreparedStatement preparedStatement;
        try (Connection connection = ConnectorDB.createConnection()){
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1,post.getUserId());
            preparedStatement.setString(2,post.getTitle());
            preparedStatement.setString(3,post.getContent());
            preparedStatement.setTimestamp(4,Timestamp.valueOf(post.getCreated()));
            preparedStatement.addBatch();
            preparedStatement.executeBatch();
            preparedStatement.getGeneratedKeys();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return post;
    }
}
