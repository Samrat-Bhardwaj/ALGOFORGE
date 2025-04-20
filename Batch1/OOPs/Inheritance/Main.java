class Vehicle {
    String color;
    int mileage;

    public void Honk(){
        System.out.println("Honkinggg");
    }

    public Vehicle(){
        System.out.println("Constructor from Vehicle class");
    }

    public Vehicle(String color, int mileage){
        System.out.println("Parametrized Constructor from Vehicle class");
        this.color = color;
        this.mileage = mileage;
    }
}

class Bike extends Vehicle {
    int cc;

    public Bike(){
        System.out.println("Constructor from Bike class");
    }

    public Bike(String color, int mileage, int cc){
        super(color, mileage);
        this.cc = cc;
        System.out.println("Parametrized Constructor from Bike class");
    }

    public void doBikeStuff(){
        System.out.println("Doing Bike stuff");
    }

    public void changeColor(String newColor){
        super.color = newColor;
    }
}
// multiple inheritance (NOT POSSIBLE)
class Parent1 {
    String color;
    int number;

    public void akshat(){
        System.out.pritln("Akshat in MAIT");
    }
}

class Parent2 {
    String color;
    int number2;

    public void akshat(){
        System.out.println("Akshat in DTU");
    }
}

class Child extends Parent1, Parent2 {
    int c;
}

class Main {
    public static void main(String[] args){
        Bike b = new Bike("blue", 30, 600);
        System.out.println(b.color);
        Vehicle v = new Vehicle();
        // b.color = "blue";
        // b.mileage = 30;
        // b.cc = 600;

        // b.doBikeStuff();
        // b.Honk();
    }
}