package dbConnect;

import java.sql.*;

public class dbConnection {
    private static String user = "root";
    private static String urlConnect = "jdbc:mysql://localhost:3306/human_management";
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(urlConnect, user, "");
        return con;
    }

}
