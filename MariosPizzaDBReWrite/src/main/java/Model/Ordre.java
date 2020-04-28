package Model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Ordre {
    boolean homeDelivery;
    static int orderUID= 0;
    int orderTimeLength = 0;
    String customerName = null;
    String homeAdress = null;
    int phoneNumber = 0;
    LocalDateTime datetime;
    Boolean ordreStatus;
    double totalOrdrePrice = 0;
    ArrayList<Pizza> pizzasInOrdre = new ArrayList<>();
//getter setters
    public boolean isHomeDelivery() {
        return homeDelivery;
    }


    public String getHomeAdress() {
        return homeAdress;
    }

    public void setHomeAdress(String homeAdress) {
        this.homeAdress = homeAdress;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Ordre(boolean homeDelivery, int orderTimeLength, String customerName, String homeAdress, int phoneNumber, Boolean ordreStatus, double totalOrdrePrice, ArrayList<Pizza> pizzasInOrdre) {
        this.homeDelivery = homeDelivery;
        this.orderTimeLength = orderTimeLength;
        this.customerName = customerName;
        this.homeAdress = homeAdress;
        this.phoneNumber = phoneNumber;
        this.datetime = getDatetime();
        this.ordreStatus = ordreStatus;
        this.totalOrdrePrice = totalOrdrePrice;
        this.pizzasInOrdre = pizzasInOrdre;
    }


    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public int getOrderUID() {
        return orderUID;
    }

    public int getOrderTimeLength() {
        return orderTimeLength;
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getTotalOrdrePrice() {
        return totalOrdrePrice;
    }


    public ArrayList<Pizza> getPizzasInOrdre() {
        return pizzasInOrdre;
    }

    public void setOrderUID(int orderUID) {
        this.orderUID = orderUID;
    }

    public void setOrderTimeLength(int orderTimeLength) {
        this.orderTimeLength = orderTimeLength;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }


    public void setTotalOrdrePrice(double totalOrdrePrice) {
        this.totalOrdrePrice = totalOrdrePrice;
    }

    public void setPizzasInOrdre(ArrayList<Pizza> pizzasInOrdre) {
        this.pizzasInOrdre = pizzasInOrdre;
    }

    @Override
    public String toString() {
        String format = "ID#"+  orderUID + ", "
                + datetime + ", Customer name: "
                + customerName + ", Estimated order time: "
                +getOrderTimeLength() + " Minutes, "
                + pizzasInOrdre + ", "
                + totalOrdrePrice + " DDK,-";
        if(isHomeDelivery() == true){
            format = format + "Home delivery: "+ homeDelivery+
            ", Adresse: "+ homeAdress + ", "+
            "Phone number: "+ phoneNumber;
        }
        return format;
    }
}

