class Pizza {
    private boolean isVeg;
    private boolean isCheeseAdded;
    private boolean areToppingsAdded;
    private int totalBill;

    private final int baseVegPrice = 300;
    private final int baseNonVegPrice = 400;
    private final int extraCheeseVegPrice = 50;
    private final int extraCheeseNonVegPrice = 80;
    private final int extraToppingsVegPrice = 70;
    private final int extraToppingsNonVegPrice = 100;

    public Pizza(boolean isVeg){
        this.isVeg = isVeg;
        this.isCheeseAdded = false;
        this.areToppingsAdded = false;
        this.totalBill = isVeg ? this.baseVegPrice : this.baseNonVegPrice;
    }

    public void addExtraToppings(){
        if(areToppingsAdded == true){ // if its already added,do nothing
            return;
        }

        this.areToppingsAdded = true;
        if(isVeg){
            this.totalBill += extraToppingsVegPrice;
        } else {
            this.totalBill += extraToppingsNonVegPrice;
        }
    }

    public void addExtraCheese(){
        if(isCheeseAdded == true){
            return;
        }

        this.isCheeseAdded = true;
        if(isVeg){
            this.totalBill += extraCheeseVegPrice;
        } else {
            this.totalBill += extraCheeseNonVegPrice;
        }
    }

    public int getAndPrintBill(){
        // base price 
        int basePrice = isVeg ? this.baseVegPrice : this.baseNonVegPrice;
        System.out.println("Base price of Pizza is: " + basePrice);
        // extra cheese? 

        if(isCheeseAdded){
            int cheesePrice = isVeg ? this.extraCheeseVegPrice : this.extraCheeseNonVegPrice;
            System.out.println("Extra cheese added: " + cheesePrice);
        }

        // extra toppings?
        if(areToppingsAdded){
            int toppingsPrice = isVeg ? this.extraToppingsVegPrice : this.extraToppingsNonVegPrice;
            System.out.println("Extra toppings added: " + toppingsPrice);
        }

        // total price
        System.out.println("Total amount: " + this.totalBill);

        return this.totalBill;
    }
}