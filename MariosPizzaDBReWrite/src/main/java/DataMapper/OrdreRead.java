package DataMapper;


import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class OrdreRead {
    private static final String USERNAME = "fullroot";
    private static final String PASSWORD = "fullroot";
    private static final String CONN_STR = "jdbc:mysql://cphb-gruppe1.c4mqzn3xlkdy.us-east-2.rds.amazonaws.com/";
    public int getNewPizzaID() throws SQLException {
        int tempID = 0;
        try (
                Connection conn = DriverManager.getConnection(CONN_STR, USERNAME, PASSWORD);
                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = ((Statement) stmt).executeQuery("SELECT MAX(pizza_id) FROM MariosPizza.pizzasMenu   ")
        ) {
            while (rs.next()) {
                tempID = rs.getInt("MAX(pizza_id)");

            }
            return tempID;
        }
    }
    public int getOrderHighestID() {
        int tempUID = 0;
        //'Connection', 'Statement' and 'ResultSet' are AUTO-CLOSABLE when with TRY-WITH-RESOURCES BLOCK (...)
        try (
                Connection conn = DriverManager.getConnection(CONN_STR, USERNAME, PASSWORD);
                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = ((Statement) stmt).executeQuery("SELECT MAX(pizza_OrdreID) FROM MariosPizza.Ordre")
        ) {
            while (rs.next()) {
                tempUID = rs.getInt("MAX(pizza_OrdreID)");

            }


        } catch (SQLException e) {
            //Different error messages
            System.out.println(e);
        }
        return tempUID;
    }

    public void getOrders() {
        try (
                Connection conn = DriverManager.getConnection(CONN_STR, USERNAME, PASSWORD);
                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                ResultSet rs = ((Statement) stmt).executeQuery("SELECT * FROM MariosPizza.Ordre WHERE pizza_ordre_Status = '0' or pizza_ordre_Status = '1' ")
        ) {
            while (rs.next()) {

                StringBuffer buffer = new StringBuffer();
                buffer.append("Ordre NR# " + rs.getInt("pizza_OrdreID") + " : Customer " + rs.getString("Order_Customer_Name") + ", Pizza: " + rs.getString("pizza_name") + " Ordre tid: " + rs.getTime("pizza_ordretid") + " Ordre Pris: " + rs.getDouble("pizza_price") + " Order status: " + rs.getInt("pizza_ordre_Status"));
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

    public void CalculateIncome( ) { // FIX IT !!
        double temppris= 0;
        try (

                    Connection conn = DriverManager.getConnection(CONN_STR, USERNAME, PASSWORD);
                    Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                    ResultSet rs = ((Statement) stmt).executeQuery("SELECT * FROM MariosPizza.Ordre")
            ) {
                while (rs.next()) {
                    StringBuffer buffer = new StringBuffer();
                    buffer.append(rs.getDouble("pizza_price"));
                    temppris = temppris + Double.parseDouble(buffer.toString()); //FIX
                }

            System.out.println("A total profit of: "+ temppris +" ddk");
        } catch (SQLException e) {
            System.out.println(e); //FIX IT
        }

    }

    public void getMostPolularPizza() {
        try (

                Connection conn = DriverManager.getConnection(CONN_STR, USERNAME, PASSWORD);
                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                ResultSet rs = ((Statement) stmt).executeQuery("SELECT pizza_name FROM MariosPizza.Ordre GROUP BY pizza_name ORDER BY COUNT(pizza_name) DESC LIMIT 1 ")

        ) {
            while (rs.next()) {
                StringBuffer buffer = new StringBuffer();
               String mestPopPizza =  rs.getString(1);
                System.out.println("most popularer pizza:\n "+ mestPopPizza);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }

    public void calculateToDaysIncome() {
       LocalDate date = LocalDate.now();
        double temppris= 0;
        try (
                Connection conn = DriverManager.getConnection(CONN_STR, USERNAME, PASSWORD);
                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                ResultSet rs = ((Statement) stmt).executeQuery("SELECT * FROM MariosPizza.Ordre WHERE pizza_ordretid LIKE '%"+date+"%'")
        ) {
            while (rs.next()) {
                StringBuffer buffer = new StringBuffer();
                buffer.append(rs.getDouble("pizza_price"));
                temppris = temppris + Double.parseDouble(buffer.toString()); //FIX
            }

            System.out.println("Todays profit:  "+ temppris +" ddk");
        } catch (SQLException e) {
            System.out.println(e); //FIX IT
        }
    }


}

