package DataMapper;

import Model.Order;

import java.sql.*;
import java.util.ArrayList;

public class OrderWrite {
    ArrayList<Order> temporder;
    private static final String USERNAME = "fullroot";
    private static final String PASSWORD = "fullroot";
    private static final String CONN_STR = "jdbc:mysql://cphb-gruppe1.c4mqzn3xlkdy.us-east-2.rds.amazonaws.com/";
    //"SELECT * FROM MariosPizza.Ordre WHERE pizza_pizza_ordreNR ="+UID)

    public void exportData(Order orders) throws SQLException {
        temporder = new ArrayList<>();
        temporder.add(orders);
        try {

            Date CreatedDate = new Date(System.currentTimeMillis());
            //'Connection', 'Statement' and 'ResultSet' are AUTO-CLOSABLE when with TRY-WITH-RESOURCES BLOCK (...)
            Connection conn = DriverManager.getConnection(CONN_STR, USERNAME, PASSWORD);
            String query = " INSERT INTO MariosPizza.Ordre (pizza_ordretid, pizza_ordreNR, pizza_name, pizza_price, Order_Customer_Name)"
                    + " values (?, ?, ?, ?, ?)";
            for (Order o : temporder) {
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setDate(1, CreatedDate);
                preparedStatement.setInt(2, o.getOrderUID());
                preparedStatement.setString(3,o.getPizzasInOrder().toString());
                preparedStatement.setDouble(4, o.getTotalOrderPrice());
                preparedStatement.setString(5, o.getCustomerName());
                preparedStatement.execute();
                // conn.close();
                System.out.println("Order: UID#"+ o.getOrderUID()+  " Confirmed!");
            }
        } catch (Exception e) {
            System.out.println("Database MYSQL ERROR! "+e);
        }
    }
    public void changeOrderStatus(int UID) throws SQLException {
        try {


            //'Connection', 'Statement' and 'ResultSet' are AUTO-CLOSABLE when with TRY-WITH-RESOURCES BLOCK (...)
            Connection conn = DriverManager.getConnection(CONN_STR, USERNAME, PASSWORD);

            String query = " UPDATE MariosPizza.Ordre SET pizza_ordre_Status = 1 WHERE pizza_ordreNR ="+UID;
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.execute();
            // conn.close();
            System.out.println("Order " + UID + " Has been changed to complete");
        }
        catch (Exception e){
            System.out.println("ERROR! :"+ e);
            System.out.println("Make sure your input is correct");
        }

    }
}
