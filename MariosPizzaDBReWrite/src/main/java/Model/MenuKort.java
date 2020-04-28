package Model;

import java.util.ArrayList;

public class MenuKort {
    static ArrayList<Pizza> pizzas = new ArrayList<>();
public void wipePizzas(){
    pizzas.clear();
}
    public Pizza GetPizzaByNR(int pizzanr){//finder bestemt pizza beseret på intastet nmr
        Pizza thePizza = pizzas.get(pizzanr-1);
        System.out.println(thePizza);
        return thePizza;
    }

    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(ArrayList<Pizza> pizzas) {
        this.pizzas = pizzas;
    }
}
