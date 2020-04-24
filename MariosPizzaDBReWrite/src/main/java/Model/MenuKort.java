package Model;

import java.util.ArrayList;

public class MenuKort {
    static ArrayList<Pizza> pizzas = new ArrayList<>();
public void wipePizzas(){
    pizzas.clear();
}
    public void AddPizzaToArray(ArrayList<Pizza> temp) {
        for (Pizza p : temp) {
            pizzas.add(p);
        }
    }
    public Pizza GetPizzaByNR(int pizzanr){
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
