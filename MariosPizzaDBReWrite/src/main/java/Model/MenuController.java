package Model;

import DataMapper.MenuKortRead;
import DataMapper.OrdreRead;
import DataMapper.OrdreWrite;
import java.sql.SQLException;
import java.util.Scanner;

public class MenuController {
    OrdreRead read = new OrdreRead();
    Scanner userInput = new Scanner(System.in);
    OrdreWrite nyPizza = new OrdreWrite();
    OrdreController ordreController = new OrdreController();
    MenuKort menuKort = new MenuKort();
    MenuKortRead importMenuKort = new MenuKortRead();

    public void createPizza() throws SQLException {
        int tempNewID = read.getNewPizzaID() + 1; //finder det current max "Pizza ID" fra menu korted og plusser det med 1 for at lave et nyt id.
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
                nyPizza.addNewPizzaMenu(tempNewID, tempPizzaNavn, tempPizzaPris); //tilføjer pizzaen til DB
                System.out.println("Adding Pizza");
                break;
            case 0:
                System.out.println("Aborting");
                createPizza();
                break;
            default:
                break;

        }


    } //Metode der laver vores pizza

    public void populateMenuKort() {
        menuKort.setPizzas(importMenuKort.getQueryJDBC());
    } //Læser fra DB og tilføjer alle pizzaer til vores programs  temp data aka dette skal gøres hver gang der er en ændring i menukortet eller porgrammet genstartes eller slukkes osv.

    public void updatePizza() {
        System.out.println("\nWhat Pizza Do you want to change?");
        int tempNewID = Integer.parseInt(userInput.nextLine());
        System.out.println("\nPizza To be changed:");
        ordreController.getSpecficPizza(tempNewID); //finder den pizza brugeren inputter
        System.out.println("\nWhat do you want to do?");
        System.out.println("[0] Update Pizza Name\n[1] Update Pizza Price\n[2] Update Pizza menu status");
        int input = Integer.parseInt(userInput.nextLine());
        System.out.println("\nEnter Change: ");
        String change = userInput.nextLine();
        System.out.println("Updating pizza..#" + tempNewID);
        nyPizza.modifyPizza(tempNewID, input, change); //'tempNewID' sender den ID der skal ændres 'input' hvad der skal gøres ved pizzaen 'tempid' og 'change' og hvad der skal ændres

    } //Metode der gør at brugeren kan updatere pizzaere i vores menukort

}


