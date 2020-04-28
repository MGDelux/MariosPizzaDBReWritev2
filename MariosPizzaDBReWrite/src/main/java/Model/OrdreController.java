package Model;
import DataMapper.OrdreRead;
import DataMapper.OrdreWrite;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class OrdreController { // MANY TEMP Data types og andet sjovt stuff owo
    Boolean tempHomeDelivery = true;
    double temppris = 0.0;
    static int tempUID = 0;
    OrdreRead readData = new OrdreRead();
    String tempCustomerFullName;
    int tempPhoneNumber = 0;
    String tempAddress = "";
    int tempOrderTimeLenght = 0;
    double tempPris = 0;
    LocalDateTime tempOrderTime;
    ArrayList<Pizza> tempPizza;
    MenuKort menu = new MenuKort();
    OrdreWrite writeToDB = new OrdreWrite();
    Ordre newOrdre;
    Scanner userInput = new Scanner(System.in);
    OrdreRead ordreRead = new OrdreRead();
    public void createOrder() throws SQLException {
        newOrdre = new Ordre(tempHomeDelivery, tempOrderTimeLenght, tempCustomerFullName, tempAddress, tempPhoneNumber, false, tempPris, tempPizza);
        checkIfDeliveryOrdre();
        getCustomerInfomation();
        getOrderTime();
        setOrderLength();
        addPizzasToOrder();
        generatePris();
        generateOrderUID();


    }  //vores 'største metode' der laver en ny ordre baseret på bruger input

    private void checkIfDeliveryOrdre() {
        System.out.println("Is this order going to be delivered?\n [1] For YES, [0] For NO,");
        int tempAnswer;
        tempAnswer = Integer.parseInt(userInput.nextLine());
        switch (tempAnswer) {
            case 0:
                tempHomeDelivery = false;
                newOrdre.homeDelivery = false;

                break;
            case 1:
                newOrdre.homeDelivery = true;
                tempHomeDelivery = true;
                break;
            default:
                newOrdre.homeDelivery = false;
                tempHomeDelivery = false;
                break;
        }


    } //spørg bruger om det skal være en hjemme levering eller ej

    public String getCustomerInfomation() {
        if (tempHomeDelivery == true) {
            System.out.println("\nEnter customer name:");
            tempCustomerFullName = userInput.nextLine();
            newOrdre.setCustomerName(tempCustomerFullName);
            System.out.println("\nEnter customer phone number:");
            tempPhoneNumber = Integer.parseInt(userInput.nextLine());
            newOrdre.setPhoneNumber(tempPhoneNumber);
            System.out.println("\nEnter customer Address:");
            tempAddress = userInput.nextLine();
            newOrdre.setHomeAdress(tempAddress);

            return tempCustomerFullName + tempPhoneNumber + tempPhoneNumber;
        } else
            System.out.println("Enter customer name:");
        tempCustomerFullName = userInput.nextLine();
        newOrdre.setCustomerName(tempCustomerFullName);
        return tempCustomerFullName;
    } //spørg bruger om kunde infomationen

    public LocalDateTime getOrderTime() {
        LocalDateTime now = LocalDateTime.now();
        // DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // String dateTimeFormatted = now.format(format);
        //LocalDateTime formattedcomplete = LocalDateTime.parse(now);
        newOrdre.setDatetime(now);
        tempOrderTime = now;
        return now;

    } //sætter den nuværende tid og dato OBS *dette er en gammle metode vores databasen sætter tiden nu. *slet senere

    public int setOrderLength() {
        System.out.println("\nHow long will it take to complete this order ? in minutes.");
        String tempOrderLenght = userInput.nextLine();
        int tempOrdreLength = Integer.parseInt(tempOrderLenght);
        newOrdre.setOrderTimeLength(tempOrdreLength);
        tempOrderTimeLenght = tempOrdreLength;
        return tempOrdreLength;
    } //spørg og sætter hvor lang tid det tager laver en ordre *Skal laves, er ikke kommet til reality pga feature creep

    public ArrayList<Pizza> addPizzasToOrder() {
        tempPizza = new ArrayList<>();
        System.out.println("What are the pizzas in this ordre?");
        String temppizzas = userInput.nextLine();
        String[] pizzaSplitter = temppizzas.split(",");
        int tempIpizza = pizzaSplitter.length;
        for (int i = 0; i < tempIpizza; i++) {
            tempPizza.add(menu.GetPizzaByNR(Integer.parseInt(pizzaSplitter[i])));
            newOrdre.setPizzasInOrdre(tempPizza);
        }
        return tempPizza;
    } //spørg og sætter de pizzaer bruger intaster

    public double generatePris() {
        for (Pizza p : newOrdre.getPizzasInOrdre()) {
            temppris = temppris + p.getPizzaPrice();
            newOrdre.setTotalOrdrePrice(temppris);
            tempPris = temppris;
        }
        return temppris;

    }  //laver total ordre pris baseret på pizzaerne i ordren

    public int generateOrderUID() throws SQLException {
        tempUID++;
        newOrdre.setOrderUID(tempUID);
        System.out.println(newOrdre.toString());
        completeOrder();
        return tempUID;
    } //genererer et nyt UID til ordrern basdet på databsen højeste UID value

    private void completeOrder() throws SQLException {
        writeToDB.exportData(newOrdre);

    } //expotere vores ordre data'en til vores database

    public void mySQLUIDCheck() { //
        tempUID = readData.getOrderHighestID();
    } //vores 'SQL CHECK' der bare finder den højeste ID fra ordre og sætter den som vores nye default ordreID value

    public void clearMenuKort() {
        menu.wipePizzas();
    } //SLETTER VORES LOKALE MENUKORT

    public void getPizzas() {
        ArrayList<Pizza> temp = new ArrayList<>();
        temp = menu.getPizzas();
        for (Pizza pizza : temp) {
            if (pizza.pizzaStatus == 0) {
                System.out.println("#" + pizza.pizzaNR + " " + pizza.toString() + " " + pizza.pizzaPrice + " KR.");
            }
        }
    } //henter vores lokale Menukort

    public void getSpecficPizza(int id) {
        ArrayList<Pizza> temp = new ArrayList<>();
        temp = menu.getPizzas();
        for (Pizza pizza : temp) {
            if (pizza.pizzaNR == id) {
                System.out.println("#" + pizza.pizzaNR + " " + pizza.toString() + " " + pizza.pizzaPrice + " KR.");
            }

        }
    } //finder en bestemt pizza

    public void showAllOrders() {
        ordreRead.getOrders();

    } //viser alle vores pizzaer fra DB

    public void showAllActiveOrders() {
        ordreRead.getActiveOrders();
    } //viser alle aktive ordre status:'0'

    public void changeOrder() throws SQLException {
        showAllActiveOrders();
        System.out.println("What Order do you want to change?");
        String orderInput = userInput.nextLine();
        int choice = Integer.parseInt(orderInput);
        writeToDB.changeOrderStatus(choice);

    } //tldr ændre ordre status fra 0 til 1

    public void deleteOrder() {
        showAllOrders();
        System.out.println("What Order do you want to change?\n");
        String orderInput = userInput.nextLine();
        int choice = Integer.parseInt(orderInput);
        writeToDB.flagOrdre(choice);

    } //ændre ordre status fra 0 / 1 til 2

    public void calculateIncome() {
        System.out.println("Show entire years income: Press [0], \nShow todays income: Press [1]");
        String input = userInput.nextLine();
        switch (Integer.parseInt(input)) {
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

    } //berenger ikomst fra enten idag eller totalt

    public void Getstatistics() {
        System.out.println("Please wait retrieving statistics...\n");
        ordreRead.getMostPolularPizza();
        ordreRead.getLeastPopularPizza();
        ordreRead.getTopCustomer();
    } //finder forskellige statistiker

    public void devOption() {
        System.out.println("Are you sure want to wipe the database?\n0 for no 1 for yes.");
        String orderInput = userInput.nextLine();
        int choice = Integer.parseInt(orderInput);
        if (choice == 0) {
            System.out.println("action aborted");

        } else {
            System.out.println("No going back now.\n TRUNCATING TABLE");
            writeToDB.turnCateTable();
        }


    }
}
