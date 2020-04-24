package Model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Ordre {
    static int orderUID= 0;
    int orderTimeLength = 0;
    String customerName;
    LocalDateTime datetime;
    Boolean ordreStatus;
    double totalOrdrePrice = 0;
    ArrayList<Pizza> pizzasInOrdre = new ArrayList<>();

    public Ordre(int orderUID, int orderTimeLength, String customerName, Boolean ordreStatus, double totalOrdrePrice, ArrayList<Pizza> pizzas, LocalDateTime datetime) {
        this.orderUID = orderUID;
        this.orderTimeLength = orderTimeLength;
        this.customerName = customerName;
        this.ordreStatus = ordreStatus;
        this.totalOrdrePrice = totalOrdrePrice;
        this.pizzasInOrdre = pizzas;
        this.datetime = datetime;
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

    public Boolean getOrdreStatus() {
        return ordreStatus;
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

    public void setOrdreStatus(Boolean ordreStatus) {
        this.ordreStatus = ordreStatus;
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
        return format;
    }
}

