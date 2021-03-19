package data_base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConnectorDB {
    private static final String DRIVER = "org.h2.Driver";
    private static final String URL = "jdbc:h2:file:./data_base/alibaba";
    private static final String USER = "sa";
    private final static String PASSWORD = "";

    public static Connection createConnection(){
        Connection connect = null;
        try {
            Class.forName(DRIVER);
            connect = DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connect;
    }
}
