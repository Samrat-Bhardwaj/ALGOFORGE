class Vehicle {
    String color;
    int mileage;

    public void Honk(int a){
        System.out.println("Honking in Vehicle class");
    }

    public void run(int a){
        System.out.println("Running in Vehicle class");
    }
}

class Bike extends Vehicle {
    int cc;

    // method overloading 
    public void Honk(String a){
        System.out.println("Honking in Bike class");
    }

    @Override
    public void run(int a){
        System.out.println("Running in Bike class");
    }
}

class Car extends Vehicle {
    public void Honk(int a, int b){
        System.out.println("Honking in car class");
    }
    @Override
    public void run(int a){
        System.out.println("Running in Car class");
    }
}

class Main {
    public static void main(String[] args){
        Vehicle obj = new Bike(); // upcasting
        obj.run(43);
        
        Bike b = new Bike();
        b.Honk(1);
        b.Honk("!12");
    }
}