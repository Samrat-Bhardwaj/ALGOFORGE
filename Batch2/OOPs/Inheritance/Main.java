class Vehicle {
    int number_of_wheels;
    String color;
    int horsePower;

    public Vehicle(){
        System.out.println("Vehicle class constructor is called");
    }

    public void pressHorn(){
        System.out.println("Pressing horn!!!!");
    }
}

class Car extends Vehicle {
    int handBrakeCapacity;

    public Car(){
        System.out.println("Car class constructor is called");
    }

    public void carFunction(){
        System.out.println("Car function");
    }
}

class Bike extends Vehicle {
    int legBrakeCapacity;
    
    public Bike(){
        System.out.println("Bike class constructor is called");
    }

    public void bikeFunction(String color){
        super.color = "red";
        System.out.println(color + "," + super.color);
        System.out.println("Bike function");
    }
}

class Main {
    public static void main(String[] args){
        // Bike b = new Bike();
        // Car c = new Car();

        // b.horsePower = 23;
        // b.legBrakeCapacity = 400;
        // b.bikeFunction("Blue");
        // b.pressHorn();
    }
}