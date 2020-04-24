package Model;

import DataMapper.MenuKortRead;
import DataMapper.OrdreRead;
import DataMapper.OrdreWrite;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class OrdreController { // MANY TEMP Data types
    double temppris = 0;
    static int tempUID = 0;
    OrdreRead readData = new OrdreRead();
    String tempCustomerName;
    int tempOrderTimeLenght;
    double tempPris;
    LocalDateTime tempOrderTime;
    ArrayList<Pizza> tempPizza;
    MenuKort menu = new MenuKort();
    OrdreWrite writeToDB = new OrdreWrite();
    Ordre newOrdre;
    Scanner userInput = new Scanner(System.in);
    OrdreRead ordreRead = new OrdreRead();
    MenuKortRead importMenuKort = new MenuKortRead();

    public void mySQLUIDCheck(){
        tempUID = readData.getOrderHighestID();
    }

    public void populateMenuKort(){
        menu.setPizzas(importMenuKort.getQueryJDBC());
    }
    public void getPizzas() {
        ArrayList<Pizza> temp = new ArrayList<>();
        temp = menu.getPizzas();
        for (Pizza pizza : temp) {
            if (pizza.pizzaStatus == 0) {
                System.out.println("#" + pizza.pizzaNR + " " + pizza.toString() + " " + pizza.pizzaPrice + " KR.");
            }
        }
    }
    public void getSpecficPizza(int id){
        ArrayList<Pizza> temp = new  ArrayList<>();
        temp = menu.getPizzas();
        for (Pizza pizza: temp) {
            if (pizza.pizzaNR == id) {
                System.out.println("#" + pizza.pizzaNR + " " + pizza.toString() + " " + pizza.pizzaPrice + " KR.");
            }

        }
    }

    public void showAllOrders() {
        ordreRead.getOrders();

    }

    public void makeOrder() throws SQLException {
        newOrdre = new Ordre(tempUID, tempOrderTimeLenght, tempCustomerName, false, tempPris, tempPizza, tempOrderTime);
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
        newOrdre.setCustomerName(tempCustomerName);
        return tempCustomerName;
    }

    public int generateOrderUID() throws SQLException {
        tempUID++;
        newOrdre.setOrderUID(tempUID);
        System.out.println(newOrdre.toString());
        completeOrder();
        return tempUID;
    }

    private void completeOrder() throws SQLException {
        writeToDB.exportData(newOrdre);

    }

    public LocalDateTime getOrderTime() {
        LocalDateTime now = LocalDateTime.now();
        // DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // String dateTimeFormatted = now.format(format);
        //LocalDateTime formattedcomplete = LocalDateTime.parse(now);
        newOrdre.setDatetime(now);
        tempOrderTime = now;
        return now;

    }

    public int setOrderLength() {
        System.out.println("\nHow long will it take to complete this order ? in minutes.");
        String tempOrderLenght = userInput.nextLine();
        int tempOrdreLength = Integer.parseInt(tempOrderLenght);
        newOrdre.setOrderTimeLength(tempOrdreLength);
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
            newOrdre.setPizzasInOrdre(tempPizza);
        }
        return tempPizza;
    }

    public double generatePris() {
        for (Pizza p : newOrdre.getPizzasInOrdre()) {
            temppris = temppris + p.getPizzaPrice();
            newOrdre.setTotalOrdrePrice(temppris);
            tempPris = temppris;

        }
        return temppris;

    }

    public void showAllActiveOrders() {
        ordreRead.getActiveOrders();
    }

    public void changeOrder() throws SQLException {
        showAllActiveOrders();
        System.out.println("What Order do you want to change?");
        String orderInput = userInput.nextLine();
        int choice = Integer.parseInt(orderInput);
        writeToDB.changeOrderStatus(choice);

    }

    public void deleteOrder() {
        showAllOrders();
        System.out.println("What Order do you want to change?\n");
        String orderInput = userInput.nextLine();
        int choice = Integer.parseInt(orderInput);
        writeToDB.flagOrdre(choice);

    }

    public void calculateIncome() {
        System.out.println("Show entire years income: Press [0], \nShow todays income: Press [1]");
        String input = userInput.nextLine();
        switch (Integer.parseInt(input)){
            case 0:
                System.out.println("Getting total income...");
                ordreRead.CalculateIncome();
                break;
            case 1:
                ordreRead.calculateToDaysIncome();
                break;
            default:
                System.out.println("ERROR");
                break;
        }

    }

    public void Getstatistics() {
        System.out.println("Please wait retrieving statistics...\n");
        ordreRead.getMostPolularPizza();
    }

    public void devOption() {
        System.out.println("Are you sure want to wipe the database?\n0 for no 1 for yes.");
        String orderInput = userInput.nextLine();
        int choice = Integer.parseInt(orderInput);
        if(choice==0){
            System.out.println("action aborted");

        }else {
            System.out.println("No going back now.\n TRUNCATING TABLE");
            writeToDB.turnCateTable();
        }


    }
}
