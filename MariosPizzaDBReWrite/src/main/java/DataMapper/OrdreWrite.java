package DataMapper;

import Model.Ordre;
import Model.Pizza;

import java.sql.*;
import java.util.ArrayList;

public class OrdreWrite {
    ArrayList<Ordre> temporder;
    int tempUID;
    int tempPizzaCounter = 0;
    private static final String USERNAME = "fullroot";
    private static final String PASSWORD = "fullroot";
    private static final String CONN_STR = "jdbc:mysql://cphb-gruppe1.c4mqzn3xlkdy.us-east-2.rds.amazonaws.com/";
    //"SELECT * FROM MariosPizza.Ordre WHERE pizza_pizza_ordreNR ="+UID)

    public void exportData(Ordre orders) throws SQLException {
        temporder = new ArrayList<>();
        temporder.add(orders);
        if (orders.isHomeDelivery() == true) {
            try {
                Connection conn = DriverManager.getConnection(CONN_STR, USERNAME, PASSWORD);
                for (Pizza p : orders.getPizzasInOrdre()) {
                    tempPizzaCounter++;
                    String query = " INSERT INTO MariosPizza.Ordre ( pizza_OrdreID, pizza_name, ordre_Total_Price, Order_Customer_Name, pizza_price, home_delivery)"
                            + " values (?, ?, ?, ?, ?, ?)";
                    for (Ordre o : temporder) {
                        String pizzas = orders.getPizzasInOrdre().get(tempPizzaCounter - 1).toString();
                        Pizza pizza = o.getPizzasInOrdre().get(tempPizzaCounter - 1);
                        Double getpris = pizza.getPizzaPrice();
                        PreparedStatement preparedStatement = conn.prepareStatement(query);
                        preparedStatement.setInt(1, o.getOrderUID());
                        preparedStatement.setString(2, pizzas);
                        preparedStatement.setDouble(3, o.getTotalOrdrePrice());
                        preparedStatement.setString(4, o.getCustomerName());
                        preparedStatement.setDouble(5, getpris);
                        preparedStatement.setBoolean(6,o.isHomeDelivery());
                        preparedStatement.execute();
                        tempUID = o.getOrderUID();
                    }
                }
                String query = " INSERT INTO MariosPizza.Customer_Infomation (Customer_Name, Customer_PhoneNr, Customer_Adresse)"
                        + " values (?, ?, ?)";
                PreparedStatement preparedStatement = conn.prepareStatement(query);

                preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, orders.getCustomerName());
                preparedStatement.setInt(2, orders.getPhoneNumber());
                preparedStatement.setString(3, orders.getHomeAdress());
                preparedStatement.execute();
                int tempPizzaCounter = 0;

            } catch (SQLException e) {
                System.out.println(e);

            }
            System.out.println("Order: UID#" + tempUID + " Confirmed! with " + tempPizzaCounter + " Pizzas in the order!");

        }else if (orders.isHomeDelivery() == false) {

            try {
                Connection conn = DriverManager.getConnection(CONN_STR, USERNAME, PASSWORD);
                for (Pizza p : orders.getPizzasInOrdre()) {
                    tempPizzaCounter++;
                    String query = " INSERT INTO MariosPizza.Ordre ( pizza_OrdreID, pizza_name, ordre_Total_Price, Order_Customer_Name, pizza_price, home_delivery)"
                            + " values (?, ?, ?, ?, ?, ?)";
                    for (Ordre o : temporder) {
                        String pizzas = orders.getPizzasInOrdre().get(tempPizzaCounter - 1).toString();
                        Pizza pizza = o.getPizzasInOrdre().get(tempPizzaCounter - 1);
                        Double getpris = pizza.getPizzaPrice();
                        PreparedStatement preparedStatement = conn.prepareStatement(query);
                        preparedStatement.setInt(1, o.getOrderUID());
                        preparedStatement.setString(2, pizzas);
                        preparedStatement.setDouble(3, o.getTotalOrdrePrice());
                        preparedStatement.setString(4, o.getCustomerName());
                        preparedStatement.setDouble(5, getpris);
                        preparedStatement.setBoolean(6, o.isHomeDelivery());
                        preparedStatement.execute();
                        tempUID = o.getOrderUID();
                        // conn.close();
                    }

                }
                System.out.println("Order: UID#" + tempUID + " Confirmed! with " + tempPizzaCounter + " Pizzas in the order!");
                tempPizzaCounter = 0;
            } catch (Exception e) {
                System.out.println("Database MYSQL ERROR! " + e);
            }
            Connection conn = DriverManager.getConnection(CONN_STR, USERNAME, PASSWORD);

            String query = " INSERT INTO MariosPizza.Customer_Infomation (Customer_Name, Customer_PhoneNr, Customer_Adresse)"
                    + " values (?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);

            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, orders.getCustomerName());
            preparedStatement.setInt(2, orders.getPhoneNumber());
            preparedStatement.setString(3, orders.getHomeAdress());
            preparedStatement.execute();
            int tempPizzaCounter = 0;

        }
        tempPizzaCounter = 0;

    }

    public void addNewPizzaMenu(int iD, String navn, double pris) throws SQLException {
        try {
            Date CreatedDate = new Date(System.currentTimeMillis());
            Connection conn = DriverManager.getConnection(CONN_STR, USERNAME, PASSWORD);
            String query = " INSERT INTO MariosPizza.pizzasMenu ( pizza_id, pizza_name, pizza_price)"
                    + " values (?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, iD);
            preparedStatement.setString(2, navn);
            preparedStatement.setDouble(3, pris);
            preparedStatement.execute();
            conn.close();
            System.out.println("PIZZA ADDED:\n" + iD + " " + " " + navn + " " + pris + " DDK");

        } catch (Exception e) {
            System.out.println("Database MYSQL ERROR! " + e);
        }

    }

    public void flagPizza(int nR) {
        try {
            Connection conn = DriverManager.getConnection(CONN_STR, USERNAME, PASSWORD);

            String query = " UPDATE MariosPizza.pizzasMenu SET pizza_status = 1 WHERE pizza_id =" + nR;
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.execute();
            // conn.close();
            System.out.println("\nPizza #" + nR + " Has been flagged");
        } catch (Exception e) {
            System.out.println("ERROR! :" + e);
            System.out.println("Make sure your input is correct");
        }
    }


    public void changeOrderStatus(int UID) throws SQLException {
        try {


            //'Connection', 'Statement' and 'ResultSet' are AUTO-CLOSABLE when with TRY-WITH-RESOURCES BLOCK (...)
            Connection conn = DriverManager.getConnection(CONN_STR, USERNAME, PASSWORD);

            String query = " UPDATE MariosPizza.Ordre SET pizza_ordre_Status = 1 WHERE pizza_ordreID =" + UID;
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.execute();
            // conn.close();
            System.out.println("Order #" + UID + " Has been changed to complete");
        } catch (Exception e) {
            System.out.println("ERROR! :" + e);
            System.out.println("Make sure your input is correct");
        }

    }

    public void flagOrdre(int UID) {
        try {


            //'Connection', 'Statement' and 'ResultSet' are AUTO-CLOSABLE when with TRY-WITH-RESOURCES BLOCK (...)
            Connection conn = DriverManager.getConnection(CONN_STR, USERNAME, PASSWORD);

            String query = "	UPDATE MariosPizza.Ordre SET pizza_ordre_Status = 2 WHERE pizza_OrdreID =" + UID;
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.execute();
            // conn.close();
            System.out.println("Order #" + UID + " Has been flagged");
        } catch (Exception e) {
            System.out.println("ERROR! :" + e);
            System.out.println("Make sure your input is correct");
        }
    }

    public void turnCateTable() {
        try {


            //'Connection', 'Statement' and 'ResultSet' are AUTO-CLOSABLE when with TRY-WITH-RESOURCES BLOCK (...)
            Connection conn = DriverManager.getConnection(CONN_STR, USERNAME, PASSWORD);
            System.out.println("Connection to DB ESTABLISHED...");
            String query = "TRUNCATE TABLE MariosPizza.Ordre";
            System.out.println("truncating in progress..");
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.execute();
            conn.close();
            System.out.println("Table data wipe complete..\nConnection closed..");
        } catch (Exception e) {
            System.out.println("ERROR! :" + e);
        }
    }
}
