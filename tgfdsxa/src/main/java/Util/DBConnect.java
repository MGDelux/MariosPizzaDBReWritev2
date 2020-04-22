package Util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

    private Connection connection;
    private static DBConnect instance;

    private DBConnect() {
        try {
            System.out.println("Connectig to Database...");
            String baseurl = "jdbc:mysql://cphb-gruppe1.c4mqzn3xlkdy.us-east-2.rds.amazonaws.com/";
            String db = "TestDataBase";
            String timeZ = "serverTimezone=UTC&allowPublicKeyRetrieval=true";
            String totalUrl = baseurl+db+"?"+timeZ;
            String user = "program";
            String password = "suH2B1";
            connection = DriverManager.getConnection(totalUrl,user,password);
            System.out.println("Database connection established..."+"\nDatabase: " +db);
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