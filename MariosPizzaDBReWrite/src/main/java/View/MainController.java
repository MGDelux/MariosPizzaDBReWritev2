package View;

import java.sql.SQLException;
import java.util.Scanner;

import DataMapper.MenuKortRead;
import DataMapper.OrderRead;
import Model.*;
import Util.*;

public class MainController {
    boolean sqlCheck = true;
    Scanner userInput = new Scanner(System.in);
    OrdreController ordreController = new OrdreController();

    public void printMainMenu() throws SQLException {
        initalizeSQLDB();//SQL Stuff
        String PrintMainMenuText = "\nMario's Pizzaria\n" +
                " -Menu:\n" +
                " Press: [1] To place an order. \n" +
                " Press: [2] To show all orders. \n" +
                " Press: [3] To show only active orders. \n" +
                " Press: [4] To change an active order to status 'delivered'. \n" +
                " Press: [5] To delete an order. \n" +
                " Press: [6] To calculate today's earnings. \n" +
                " Press: [7] To export today's orders to file and exit.";
        System.out.println(PrintMainMenuText);
        System.out.print("\nEnter your option: ");
        String userOption = userInput.nextLine();
        int choice = Integer.parseInt(userOption);
        switch (choice) {
            case 1:
                //  newOrder = new Order(tempUID, tempOrderTimeLenght, tempCustomerName, false, tempPris, tempPizza, tempOrderTime);
                ordreController.makeOrder();
                printMainMenu();
                break;
            case 2:
                ordreController.showAllOrders();
                printMainMenu();
                break;
            case 3:
                ordreController.showAllActiveOrders();
                printMainMenu();
                break;
            case 4:
                  ordreController.changeOrder();
                printMainMenu();
                break;
            case 5:
                //   orderBook.deleteOrder(orderBook.getOrders());

                break;
            case 6:
                //    orderBook.calculateIncome();
                break;
            case 7:
                //  write.exportData(orderBook.getOrders());
                break;

            default:
                printMainMenu();
                break;


        }

    }

    private void initalizeSQLDB() {
        if (sqlCheck == true) {
            try {
                sqlCheck = false;
                System.out.println("Attempting to Instance Database...");
                DBConnect.getInstance(); // Checking Connection
                ordreController.populateMenuKort();
                ordreController.mySQLUIDCheck();
                System.out.println("SQL connected and Checks complete");
            } catch (Exception e) {
                System.out.println("MYSQL ERROR! :" + e);
                sqlCheck = true;
            }


        }


    }
}
