class Vehicle {
    String color;
    int mileage = 60;

    public void Honk(){
        System.out.println("Honkinggg");
    }

    public Vehicle(){
        System.out.println("Constructor from Vehicle class");
    }
}

class Car extends Vehicle {
    int horsePower = 1500;

    public Car(){
        System.out.println("Car constructor");
    }

    public void race(){
        System.out.println("Lucky, the racer is racing");
    }
}

class Ferrari extends Car {
    int modelNumber = 56;
    
    public Ferrari(){
        System.out.println("Ferrari constructor");
    }

    public void FerrariNoise(){
        System.out.println("Ford is a loser!!!");
    }
}

class MultiLevelInheritance {
    public static void main(String[] args){
        Ferrari f1 = new Ferrari();
        System.out.println(f1.mileage + " , " + f1.horsePower + "," + f1.modelNumber);
        f1.race();
    }
}