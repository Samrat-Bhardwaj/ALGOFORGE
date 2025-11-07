class Main {
    public static void main(String[] args){
        Pizza p = new Pizza(false);

        p.addExtraCheese();
        p.addExtraCheese();
        
        p.addExtraToppings();
        p.addExtraCheese();

        p.getAndPrintBill();

        DeluxePizza dp = new DeluxePizza(true);
        dp.addExtraCheese();
        dp.addExtraCheese();
        dp.addExtraCheese();
        dp.addExtraToppings();

        dp.getAndPrintBill();
    }
}