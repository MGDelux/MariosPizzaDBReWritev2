package DataMapper;

import Model.Pizza;

import java.sql.*;
import java.util.ArrayList;

public class OrderRead {
    private static final String USERNAME = "fullroot";
    private static final String PASSWORD = "fullroot";
    private static final String CONN_STR = "jdbc:mysql://cphb-gruppe1.c4mqzn3xlkdy.us-east-2.rds.amazonaws.com/";

    public int getOrderHighestID() {
        int tempUID = 0;
        //'Connection', 'Statement' and 'ResultSet' are AUTO-CLOSABLE when with TRY-WITH-RESOURCES BLOCK (...)
        try (
                Connection conn = DriverManager.getConnection(CONN_STR, USERNAME, PASSWORD);
                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = ((Statement) stmt).executeQuery("SELECT MAX(pizza_ordreNR) FROM MariosPizza.Ordre")
        ) {
            while (rs.next()) {
                tempUID = rs.getInt("MAX(pizza_ordreNR)");

            }


        } catch (SQLException e) {
            //Different error messages
            System.out.println(e);
        }
        return tempUID;
    }

    public void getMenuKort() {
        try (
                Connection conn = DriverManager.getConnection(CONN_STR, USERNAME, PASSWORD);
                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                ResultSet rs = ((Statement) stmt).executeQuery("SELECT * FROM MariosPizza.Ordre")
        ) {
            while (rs.next()) {

                StringBuffer buffer = new StringBuffer();
                buffer.append("Ordre NR# " + rs.getInt("pizza_ordreNR") + " : Customer " + rs.getString("Order_Customer_Name") + ", Pizza: " + rs.getString("pizza_name") + " Ordre tid: " + rs.getTime("pizza_ordretid") + " Ordre Pris: " + rs.getDouble("pizza_price") + " Order status: " + rs.getInt("pizza_ordre_Status"));
                System.out.println(buffer.toString());
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void getActiveOrders() {
        try (
                Connection conn = DriverManager.getConnection(CONN_STR, USERNAME, PASSWORD);
                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                ResultSet rs = ((Statement) stmt).executeQuery("SELECT * FROM MariosPizza.Ordre WHERE pizza_ordre_Status = 0")
        ) {
            while (rs.next()) {

                StringBuffer buffer = new StringBuffer();
                buffer.append("Ordre NR# " + rs.getInt("pizza_ordreNR") + " : Customer " + rs.getString("Order_Customer_Name") + ", Pizza: " + rs.getString("pizza_name") + " Ordre tid: " + rs.getTime("pizza_ordretid") + " Ordre Pris: " + rs.getDouble("pizza_price") + " Order status: " + rs.getInt("pizza_ordre_Status"));
                System.out.println(buffer.toString());
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}

