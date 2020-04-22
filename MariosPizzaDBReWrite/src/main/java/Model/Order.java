package Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Order {
    static int orderUID= 0;
    int orderTimeLength = 0;
    String customerName;
    LocalDateTime datetime;
    Boolean orderStatus;
    double totalOrderPrice = 0;
    ArrayList<Pizza> pizzasInOrder = new ArrayList<>();

    public Order(int orderUID, int orderTimeLength, String customerName, Boolean orderStatus, double totalOrderPrice, ArrayList<Pizza> pizzas, LocalDateTime datetime) {
        this.orderUID = orderUID;
        this.orderTimeLength = orderTimeLength;
        this.customerName = customerName;
        this.orderStatus = orderStatus;
        this.totalOrderPrice = totalOrderPrice;
        this.pizzasInOrder = pizzas;
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

    public Boolean getOrderStatus() {
        return orderStatus;
    }

    public double getTotalOrderPrice() {
        return totalOrderPrice;
    }


    public ArrayList<Pizza> getPizzasInOrder() {
        return pizzasInOrder;
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

    public void setOrderStatus(Boolean orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setTotalOrderPrice(double totalOrderPrice) {
        this.totalOrderPrice = totalOrderPrice;
    }

    public void setPizzasInOrder(ArrayList<Pizza> pizzasInOrder) {
        this.pizzasInOrder = pizzasInOrder;
    }

    @Override
    public String toString() {
        String format = "ID#"+  orderUID + ", "
                + datetime + ", Customer name: "
                + customerName + ", Estimated order time: "
                +getOrderTimeLength() + " Minutes, "
                + pizzasInOrder + ", "
                + totalOrderPrice + " DDK,-";
        return format;
    }
}

