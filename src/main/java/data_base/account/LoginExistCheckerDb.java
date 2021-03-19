package data_base.account;

import data_base.ConnectorDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginExistCheckerDb {
    public boolean isLoginExist(String login) {
        String query = "Select * from user where login = ? ";
        PreparedStatement preparedStatement = null;
        try (Connection connect = ConnectorDB.createConnection()){
            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1,login);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
