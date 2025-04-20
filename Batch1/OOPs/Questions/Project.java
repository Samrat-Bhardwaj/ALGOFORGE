class Pizza {
    boolean isVeg;
    boolean isToppingsAdded;
    boolean isCheeseAdded;
    boolean isBagAdded;
    int totalBillAmount = 0;
    String billStatement;
    private int vegBasePrice = 300;
    private int nonVegBasePrice = 400;

    public Pizza(boolean isVeg){
        this.isVeg = isVeg;
        int currentBasePrice;
        if(this.isVeg == true){
            currentBasePrice = this.vegBasePrice;
        } else {
            currentBasePrice = this.nonVegBasePrice;
        }
        String pizzaType = this.isVeg ? "Veg" : "Non-veg";
        this.billStatement = "Base price of " + pizzaType + " Pizza: " + currentBasePrice + "\n";
        this.totalBillAmount += currentBasePrice;
    }

    public void addExtraToppings(){
        if(this.isToppingsAdded == false){
            if(this.isVeg == true){
                totalBillAmount += 70;
                this.billStatement += "Extra toppings added: 70\n";
            } else {
                this.billStatement += "Extra toppings added: 100\n";
                totalBillAmount += 100;
            }

            this.isToppingsAdded = true;
        }
    } 

    public void addExtraCheese(){
        if(this.isCheeseAdded == false){
            this.billStatement += "Extra cheese added: 80\n";
            this.totalBillAmount += 80;

            this.isCheeseAdded = true;
        }
    }

    public void addTakeAway(){
        if(this.isBagAdded == false){
            this.billStatement += "Bag added: 20\n";
            this.totalBillAmount += 20;
            this.isBagAdded = true;
        }
    }

    public String getBill(){
        this.billStatement += "Total Bill is: " + this.totalBillAmount + "\n";
        return this.billStatement;
    }
}

class DeluxePizza extends Pizza {

    public DeluxePizza(boolean isVeg){
        super(isVeg);
        super.addExtraToppings();
        super.addExtraCheese();
    }
}

class Project {
    public static void main(String[] args){
        Pizza p = new Pizza(true); // true -> veg, false-> non-veg
        p.addExtraToppings();
        p.addExtraCheese();
        p.addTakeAway();

        System.out.println(p.getBill());

        DeluxePizza dp = new DeluxePizza(false);
        dp.addExtraCheese();
        dp.addTakeAway();
        System.out.println(dp.getBill());
    }
}