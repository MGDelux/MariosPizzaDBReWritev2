package Model;

public class Pizza {
    int pizzaNR;
    String pizzaName;
    double pizzaPrice;
    int pizzaStatus;

    public Pizza(int pizzaNR, String pizzaName, double pizzaPrice,int pizzaStatus) {
        this.pizzaNR = pizzaNR;
        this.pizzaName = pizzaName;
        this.pizzaPrice = pizzaPrice;
        this.pizzaStatus = pizzaStatus;
    }

    public int getPizzaStatus() {
        return pizzaStatus;
    }

    public void setPizzaStatus(int pizzaStatus) {
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
