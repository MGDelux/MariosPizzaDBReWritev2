package Model;

import DataMapper.MenuKortRead;
import DataMapper.OrderRead;
import DataMapper.OrderWrite;

import java.security.PublicKey;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class OrdreController {
    static int tempUID = 0;
    OrderRead readData = new OrderRead();
    String tempCustomerName;
    int tempOrderTimeLenght;
    double tempPris;
    LocalDateTime tempOrderTime;
    ArrayList<Pizza> tempPizza;
    MenuKort menu = new MenuKort();
    OrderWrite exportOrder = new OrderWrite();
    Order newOrder;
    Scanner userInput = new Scanner(System.in);
    OrderRead orderRead = new OrderRead();
    MenuKortRead importMenuKort = new MenuKortRead();

    public void mySQLUIDCheck(){
        tempUID = readData.getOrderHighestID();
    }

    public void populateMenuKort(){
        menu.setPizzas(importMenuKort.getQueryJDBC());
    }

    public void showAllOrders() {
        orderRead.getMenuKort();

    }

    public void makeOrder() throws SQLException {
        newOrder = new Order(tempUID, tempOrderTimeLenght, tempCustomerName, false, tempPris, tempPizza, tempOrderTime);
        getCustomerName();
        getOrderTime();
        setOrderLength();
        addPizzasToOrder();
        generatePris();
        generateOrderUID();

    }

    public String getCustomerName() {
        System.out.println("Type customer name:");
        tempCustomerName = userInput.nextLine();
        newOrder.setCustomerName(tempCustomerName);
        return tempCustomerName;
    }

    public int generateOrderUID() throws SQLException {
        tempUID++;
        newOrder.setOrderUID(tempUID);
        System.out.println(newOrder.toString());
        completeOrder();
        return tempUID;
    }

    private void completeOrder() throws SQLException {
        exportOrder.exportData(newOrder);

    }

    public LocalDateTime getOrderTime() {
        LocalDateTime now = LocalDateTime.now();
        // DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // String dateTimeFormatted = now.format(format);
        //LocalDateTime formattedcomplete = LocalDateTime.parse(now);
        newOrder.setDatetime(now);
        tempOrderTime = now;
        return now;

    }

    public int setOrderLength() {
        System.out.println("\nHow long will it take to complete this order ? in minutes.");
        String tempOrderLenght = userInput.nextLine();
        int tempOrdreLength = Integer.parseInt(tempOrderLenght);
        newOrder.setOrderTimeLength(tempOrdreLength);
        tempOrderTimeLenght = tempOrdreLength;
        return tempOrdreLength;
    }

    public ArrayList<Pizza> addPizzasToOrder() {
        tempPizza = new ArrayList<>();
        System.out.println("How many Pizzas are there in this order?");
        String temppizzas = userInput.nextLine();
        String[] pizzaSplitter = temppizzas.split(",");
        int tempIpizza = pizzaSplitter.length;
        for (int i = 0; i < tempIpizza; i++) {
            tempPizza.add(menu.GetPizzaByNR(Integer.parseInt(pizzaSplitter[i])));
            newOrder.setPizzasInOrder(tempPizza);
        }
        return tempPizza;
    }

    public double generatePris() {
        double pris = 0;
        for (Pizza p : newOrder.getPizzasInOrder()) {
            pris = pris + p.getPizzaPrice();
            newOrder.setTotalOrderPrice(pris);
            tempPris = pris;

        }
        return pris;

    }

    public void showAllActiveOrders() {
        orderRead.getActiveOrders();
    }

    public void changeOrder() throws SQLException {
        showAllActiveOrders();
        System.out.println("What Order do you want to change?");
        String orderInput = userInput.nextLine();
        int choice = Integer.parseInt(orderInput);
        exportOrder.changeOrderStatus(choice);

    }
}
