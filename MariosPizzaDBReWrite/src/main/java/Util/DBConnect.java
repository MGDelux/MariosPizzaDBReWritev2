package Util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    private Connection connection;
    private static DBConnect instance;
    private DBConnect() {
        try {
            String baseurl = "jdbc:mysql://cphb-gruppe1.c4mqzn3xlkdy.us-east-2.rds.amazonaws.com/";
            String db = "MariosPizza";
            String timeZ = "serverTimezone=UTC&allowPublicKeyRetrieval=true";
            String totalUrl = baseurl+db+"?"+timeZ;
            String user = "fullroot";
            String password = "fullroot";
            connection = DriverManager.getConnection(totalUrl,user,password);
        } catch (SQLException id) {
            System.out.println("Error: " + id.getMessage());
        }
    }
    public static DBConnect getInstance() {
        if (instance == null ) {
            instance = new DBConnect();
        }
        return instance;
    }

    public Connection getConnection() {
        return this.connection;
    }
}