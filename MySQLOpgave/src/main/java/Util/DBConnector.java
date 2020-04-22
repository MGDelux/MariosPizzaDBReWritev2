package Util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    private Connection connection;
    private static DBConnector instance;

    private DBConnector() {
        try {
            String baseurl = "jdbc:mysql:cphb-gruppe1.c4mqzn3xlkdy.us-east-2.rds.amazonaws.com/";
            String db = "Adventuregame";
            String timeZ = "serverTimezone=UTC&allowPublicKeyRetrieval=true";
            String totalUrl = baseurl+db+"?"+timeZ;
            String user = "program";
            String password = "wa5uWO";
            connection = DriverManager.getConnection(totalUrl,user,password);
        } catch (SQLException id) {
            System.out.println("Wrong " + id.getMessage());
        }
    }
    public static DBConnector getInstance() {
        if (instance == null ) {
            instance = new DBConnector();
        }
        return instance;
    }

    public Connection getConnection() {
        return this.connection;
    }
}