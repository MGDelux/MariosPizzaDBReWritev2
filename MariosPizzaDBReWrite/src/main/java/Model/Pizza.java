package Model;

public class Pizza {
    int pizzaNR;
    String pizzaName;
    double pizzaPrice;

    public Pizza(int pizzaNR, String pizzaName, double pizzaPrice) {
        this.pizzaNR = pizzaNR;
        this.pizzaName = pizzaName;
        this.pizzaPrice = pizzaPrice;
    }

    public int getPizzaNR() {
        return pizzaNR;
    }

    public void setPizzaNR(int pizzaNR) {
        this.pizzaNR = pizzaNR;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public void setPizzaName(String pizzaName) {
        this.pizzaName = pizzaName;
    }

    public double getPizzaPrice() {
        return pizzaPrice;
    }

    public void setPizzaPrice(double pizzaPrice) {
        this.pizzaPrice = pizzaPrice;
    }

    @Override
    public String toString() {
        return "'" + pizzaName+"'";
    }
}
