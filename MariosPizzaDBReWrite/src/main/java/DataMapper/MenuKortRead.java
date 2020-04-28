package DataMapper;
import Model.Pizza;
import Util.DBConnect;

import java.sql.*;
import java.util.ArrayList;

public class MenuKortRead {
    Connection connection = DBConnect.getInstance().getConnection(); //Ny connection metode der bruger vores DBConnect instance istedet for gamle metode :)
    ArrayList<Pizza> tempPizzas = new ArrayList<Pizza>();
    public ArrayList<Pizza> getQueryJDBC() {
        try (
                Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = ((Statement) stmt).executeQuery("SELECT * FROM MariosPizza.pizzasMenu")
        ) {
            while (rs.next()) {

                StringBuffer buffer = new StringBuffer();
                buffer.append("Pizza: " + rs.getInt("pizza_id") + " : " + rs.getString("pizza_name") + ", Price: " + rs.getDouble("pizza_price"));
                Pizza temp = new Pizza(rs.getInt("pizza_id"), rs.getString("pizza_name"), rs.getDouble("pizza_price"), rs.getInt("pizza_status"));
                tempPizzas.add(temp);
            }
        } catch (SQLException e) {
            //Different error messages
            System.out.println(e);
        }
        return tempPizzas;
    }
}
