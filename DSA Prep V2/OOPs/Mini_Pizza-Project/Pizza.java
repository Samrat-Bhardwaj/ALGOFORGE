class Pizza {
    private int vegBasePrice = 250;
    private int nonVegBasePrice = 400;
    private int extraCheeseVegPrice = 70;
    private int extraCheeseNonVegPrice = 100;
    private int extraToppingsVegPrice = 50;
    private int extraToppingsNonVegPrice = 80;
    private boolean isCheeseAdded = false;
    private boolean isToppingsAdded = false;

    boolean isVeg;
    int totalPrice;

    public Pizza(boolean isVeg){
        this.isVeg = isVeg;
        createBaseOfPizze();
    }

    public void createBaseOfPizze(){
        if(this.isVeg){
            this.totalPrice += this.vegBasePrice;
        } else {
            this.totalPrice += this.nonVegBasePrice;
        }

        int basePrice = this.isVeg ? vegBasePrice : nonVegBasePrice;
        System.out.println("Price of Base: " + basePrice);
    }

    public void addExtraCheese(){
        if(this.isCheeseAdded == true){
            return;
        }

        if(this.isVeg){
            totalPrice += extraCheeseVegPrice;
        } else {
            totalPrice += extraCheeseNonVegPrice;
        }

        this.isCheeseAdded = true;

        int cheesePrice = this.isVeg ? extraCheeseVegPrice : extraCheeseNonVegPrice;
        System.out.println("Price of extra cheese: " + cheesePrice);
    }

    public void addExtraToppings(){
        if(this.isToppingsAdded == true){
            return;
        }

        int toppingsPrice = 0;
        if(this.isVeg){
            toppingsPrice = extraToppingsVegPrice;
        } else {
            toppingsPrice = extraToppingsNonVegPrice;
        }

        totalPrice += toppingsPrice;
        this.isToppingsAdded = true;

        System.out.println("Extra topping price: " + toppingsPrice);
    }

    public double getBill(){
        double gst = this.totalPrice*0.05;
        double totalAmountWithGst = this.totalPrice + gst;

        System.out.println("Total Amount: " + this.totalPrice);
        System.out.println("Total GST: " + gst);
        System.out.println("Total amount to Pay: " + totalAmountWithGst);
        
        return totalAmountWithGst;
    }
}