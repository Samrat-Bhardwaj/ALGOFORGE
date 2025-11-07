class Vehicle {
    public String color;
    private int price;

    public void changePrice(){
        this.price = 23344; // within class you can access private attributes
    }
}

class PrivateExample {
    public static void main(String[] args){
        Vehicle v = new Vehicle();

        v.color = "Red";
        // v.price = 1000; ->error: price has private access in Vehicle
        System.out.println(v.color);
    }
}