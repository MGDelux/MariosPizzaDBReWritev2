package Util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    private Connection connection;
    private static DBConnector instance;

    public DBConnector() {
        try {
            String baseurl = "jdbc:cphb-gruppe1.c4mqzn3xlkdy.us-east-2.rds.amazonaws.com";
            String db = "TestDataBase";
            String timeZ = "serverTimezone=UTC&allowPublicKeyRetrieval=true";
            String totalUrl = baseurl+db+"?"+timeZ;
            String user = "MarioPizzaBruger";
            String password = "Yogani";
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