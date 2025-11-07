class DeluxePizza extends Pizza {

    public DeluxePizza(boolean isVeg){
        super(isVeg);
        super.addExtraCheese();
        super.addExtraToppings();
    }
}