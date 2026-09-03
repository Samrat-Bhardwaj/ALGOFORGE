class Vehicle {
    int number_of_wheels;
    
    public void honk(int a){
        System.out.println("Honking from Vehicle class");
    }

    public void run(String a){
        System.out.println("Running from class Vehicle");
    }
}

class Car extends Vehicle {
    int handBrakeCapacity;


    public void honk(String a){
        System.out.println("Honking from Car class");
    }

    @Override
    public void run(String a){
        System.out.println("Running from class Car");
    }
}

class Bike extends Vehicle {
    int cc;

    @Override
    public void run(String a){
        System.out.println("Running from class Bike");
    }
}

class Main {
    public static void main(String[] args){
        // Car c = new Car();
        // c.honk("23"); // method overloading, compile time polymorphism

        Vehicle v;

        v = new Car(); // what is actual object type of v, we will know at run time -> runTime polymorphism 
        v.run("23");
    }
}
