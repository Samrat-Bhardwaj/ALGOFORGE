class Main {
    public static void main(String[] args){
        Pizza p1 = new Pizza(true);

        p1.addExtraToppings();
        p1.addExtraCheese();
        p1.addExtraCheese();
        p1.addExtraCheese();
        p1.addExtraToppings();
        p1.addExtraToppings();
        p1.getBill();

        DeluxePizza dp = new DeluxePizza(false);
        dp.addExtraCheese();
        dp.addExtraCheese();
        dp.addExtraToppings();
        dp.getBill();
    }
}