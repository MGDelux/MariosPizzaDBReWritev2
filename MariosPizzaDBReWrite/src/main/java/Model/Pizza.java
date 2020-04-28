package Model;

public class Pizza {
    int pizzaNR;
    String pizzaName;
    double pizzaPrice;
    int pizzaStatus;

    public Pizza(int pizzaNR, String pizzaName, double pizzaPrice,int pizzaStatus) {//datatype
        this.pizzaNR = pizzaNR;
        this.pizzaName = pizzaName;
        this.pizzaPrice = pizzaPrice;
        this.pizzaStatus = pizzaStatus;
    }
    public double getPizzaPrice() {
        return pizzaPrice;
    }

    @Override
    public String toString() {
        return pizzaName;
    }
}
