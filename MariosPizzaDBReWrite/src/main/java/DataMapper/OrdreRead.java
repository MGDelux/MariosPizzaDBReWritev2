package DataMapper;
import Util.DBConnect;

import java.sql.*;
import java.time.LocalDate;

public class OrdreRead {
    Connection connection = DBConnect.getInstance().getConnection(); //Ny connection metode der bruger vores DBConnect instance istedet for gamle metode :)


    public int getNewPizzaID() throws SQLException {
        int tempID = 0;
        try (
                Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = ((Statement) stmt).executeQuery("SELECT MAX(pizza_id) FROM MariosPizza.pizzasMenu   ")
        ) {
            while (rs.next()) {
                tempID = rs.getInt("MAX(pizza_id)");

            }
            return tempID;
        }
    } //finder højeste ID fra pizzaMenu

    public int getOrderHighestID() {
        int tempUID = 0;
        //'Connection', 'Statement' and 'ResultSet' are AUTO-CLOSABLE when with TRY-WITH-RESOURCES BLOCK (...)
        try (
                Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
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
    } //finder højeste ID fra ordre

    public void getOrders() {
        int tempNewOrdreID = 0;
        int tempPrevOrdreID = 0;
        String tempCustomer = "";
        double tempTotalPrice = 0;
        Timestamp tempDate;
        String tempPizzasInOrders = "";
        int tempOdreStatus;
        try (
                Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = ((Statement) stmt).executeQuery("SELECT * FROM MariosPizza.Ordre WHERE pizza_ordre_Status = '0' or pizza_ordre_Status = '1' ")
        ) {
            while (rs.next()) {
                tempNewOrdreID = rs.getInt("pizza_OrdreID");
                tempCustomer = rs.getString("Order_Customer_Name");
                tempTotalPrice = rs.getDouble("ordre_Total_Price");
                tempOdreStatus = rs.getInt("pizza_ordre_Status");
                tempDate = rs.getTimestamp("pizza_ordretid");
                if (tempNewOrdreID != tempPrevOrdreID) {
                    tempPizzasInOrders = getPizzasInOrder(tempNewOrdreID);
                    System.out.println("Ordre #" + tempNewOrdreID + " " + tempCustomer + " " + tempTotalPrice + " DDK,- "+ "Ordre status: "+ tempOdreStatus + "\n Ordre placeret den. ("+ tempDate +") Pizzas: \n"+ tempPizzasInOrders + "\n");

                }
                tempPrevOrdreID = tempNewOrdreID;


                //buffer.append("Ordre NR# " + rs.getInt("pizza_OrdreID") + " : Customer " + rs.getString("Order_Customer_Name") + ", Pizza: " + rs.getString("pizza_name") + " Ordre tid: " + rs.getTime("pizza_ordretid") + " Ordre Pris: " + rs.getDouble("pizza_price") + " Order status: " + rs.getInt("pizza_ordre_Status"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    } //skriver alle ordre ud til brugeren

    public String getPizzasInOrder(int tempOrderID) {
        String tempPizzas = "";
        try (
                Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = ((Statement) stmt).executeQuery("SELECT * FROM MariosPizza.Ordre WHERE pizza_ordre_Status IN (0,1) AND pizza_OrdreID = " + tempOrderID)
        ) {
            while (rs.next()) {
                tempPizzas = tempPizzas + " " + rs.getString("pizza_name")+ ", ";
            }
            return  tempPizzas;

        } catch (SQLException e) {
            System.out.println(e);
        }
        return tempPizzas;
    } //finder bestemte pizzaer fra en ordre

    public void getActiveOrders() {
        int tempNewOrdreID = 0;
        int tempPrevOrdreID = 0;
        String tempCustomer = "";
        double tempTotalPrice = 0;
        Timestamp tempDate;
        String tempPizzasInOrders = "";
        int tempOdreStatus;
        try (
                Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = ((Statement) stmt).executeQuery("SELECT * FROM MariosPizza.Ordre WHERE pizza_ordre_Status = '0' ")
        ) {
            while (rs.next()) {
                tempNewOrdreID = rs.getInt("pizza_OrdreID");
                tempCustomer = rs.getString("Order_Customer_Name");
                tempTotalPrice = rs.getDouble("ordre_Total_Price");
                tempOdreStatus = rs.getInt("pizza_ordre_Status");
                tempDate = rs.getTimestamp("pizza_ordretid");
                if (tempNewOrdreID != tempPrevOrdreID) {
                    tempPizzasInOrders = getPizzasInOrder(tempNewOrdreID);
                    System.out.println("Ordre #" + tempNewOrdreID + " " + tempCustomer + " " + tempTotalPrice + " DDK,- "+ "Ordre status: "+ tempOdreStatus + "\n Ordre placeret den. ("+ tempDate +") Pizzas: \n"+ tempPizzasInOrders + "\n");

                }
                tempPrevOrdreID = rs.getInt("pizza_OrdreID");


                //buffer.append("Ordre NR# " + rs.getInt("pizza_OrdreID") + " : Customer " + rs.getString("Order_Customer_Name") + ", Pizza: " + rs.getString("pizza_name") + " Ordre tid: " + rs.getTime("pizza_ordretid") + " Ordre Pris: " + rs.getDouble("pizza_price") + " Order status: " + rs.getInt("pizza_ordre_Status"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    } //skriver alle AKTIVE '0' ordre ud til brugeren

    public void CalculateIncome() { // FIX IT !!
        double temppris = 0;
        try (

                Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                ResultSet rs = ((Statement) stmt).executeQuery("SELECT * FROM MariosPizza.Ordre")
        ) {
            while (rs.next()) {
                StringBuffer buffer = new StringBuffer();
                buffer.append(rs.getDouble("pizza_price"));
                temppris = temppris + Double.parseDouble(buffer.toString()); //FIX
            }

            System.out.println("A total profit of: " + temppris + " ddk");
        } catch (SQLException e) {
            System.out.println(e); //FIX IT
        }

    } //læser all ze monies

    public void getMostPolularPizza() {
        try (

                Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                ResultSet rs = ((Statement) stmt).executeQuery("SELECT pizza_name FROM MariosPizza.Ordre GROUP BY pizza_name ORDER BY COUNT(pizza_name) DESC LIMIT 1 ")

        ) {
            while (rs.next()) {
                StringBuffer buffer = new StringBuffer();
                String mestPopPizza = rs.getString(1);
                System.out.println("most popular pizza:\n " + mestPopPizza + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }  //finder den mest populare pizza

    public void calculateToDaysIncome() {
        LocalDate date = LocalDate.now();
        double temppris = 0;
        try (
                Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                ResultSet rs = ((Statement) stmt).executeQuery("SELECT * FROM MariosPizza.Ordre WHERE pizza_ordretid LIKE '%" + date + "%'")
        ) {
            while (rs.next()) {
                StringBuffer buffer = new StringBuffer();
                buffer.append(rs.getDouble("pizza_price"));
                temppris = temppris + Double.parseDouble(buffer.toString()); //FIX
            }

            System.out.println("Todays profit:  " + temppris + " ddk");
        } catch (SQLException e) {
            System.out.println(e); //FIX IT
        }
    } //finder den mest shait pizza

    public void getTopCustomer() {
        try (

                Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                ResultSet rs = ((Statement) stmt).executeQuery("SELECT Customer_Name FROM MariosPizza.Customer_Infomation GROUP BY Customer_Name ORDER BY COUNT(Customer_Name) DESC LIMIT 1 ")

        ) {
            while (rs.next()) {
                StringBuffer buffer = new StringBuffer();
                String mostCommonCustomer = rs.getString(1);
                System.out.println("most common Customer:\n " + mostCommonCustomer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } //finder den person med højst chance for diabetes

    public void getLeastPopularPizza() {
        try (

                Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                ResultSet rs = ((Statement) stmt).executeQuery("SELECT pizza_name FROM MariosPizza.Ordre GROUP BY pizza_name ORDER BY MIN(pizza_name) DESC LIMIT 1 ")

        ) {
            while (rs.next()) {
                StringBuffer buffer = new StringBuffer();
                String leastPopPizza = rs.getString(1);
                System.out.println("least popular pizza:\n " + leastPopPizza + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } //finder den mest shait pizza


}


