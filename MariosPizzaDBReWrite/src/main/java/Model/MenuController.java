package Model;

import DataMapper.OrdreRead;
import DataMapper.OrdreWrite;

import java.sql.SQLException;
import java.util.Scanner;

public class MenuController {
    OrdreRead read = new OrdreRead();
    Scanner userInput = new Scanner(System.in);
    OrdreWrite nyPizza = new OrdreWrite();
    OrdreController me = new OrdreController();

    public void createPizza() throws SQLException {
        int tempNewID = read.getNewPizzaID() + 1;
        String tempPizzaNavn = "";
        double tempPizzaPris = 0;
        System.out.println("\n\nHvad hedder denne nye Pizza?");
        tempPizzaNavn = userInput.nextLine();
        System.out.println("->" + tempPizzaNavn + "\n");
        System.out.println("Hvad koster denne pizza?");
        tempPizzaPris = Double.parseDouble(userInput.nextLine());
        System.out.println("Ny Pizza: \nID#" + tempNewID + "\nNavn: " + tempPizzaNavn + "\nPris: " + tempPizzaPris + "\n Er dette korrekt?");
        System.out.println("[0] for NO. [1] for YES.");
        switch (Integer.parseInt(userInput.nextLine())) {
            case 1:
                nyPizza.addNewPizzaMenu(tempNewID, tempPizzaNavn, tempPizzaPris);
                System.out.println("Adding Pizza");
                break;
            case 0:
                System.out.println("Aborting");
                createPizza();
                break;
            default:
                break;

        }


    }

    public void deltetePizza() {
        System.out.println("\nWhat Pizza Do you want to remove the menu?");
        int tempNewID = Integer.parseInt(userInput.nextLine());
        System.out.println("\nPizza To be deleted:");
        me.getSpecficPizza(tempNewID);
        System.out.println("Correct?\n [0] For yes, [1] For no");
        switch (Integer.parseInt(userInput.nextLine())) {
            case 0:
                nyPizza.flagPizza(tempNewID);
                break;
            case 1:
                System.out.println("Aborting..");
                deltetePizza();
                break;
            default:
                break;
        }


    }
}
