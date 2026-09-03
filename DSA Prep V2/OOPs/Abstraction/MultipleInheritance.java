interface Vehicle {
    int a = 45;
    public void honk();
}

interface Car {
    int a = 50;
    public void honk();
}

class Mahindra implements Vehicle, Car {
    int numberOfWheels = 4;

    public void honk(){
        this.a++; // error: reference to a is ambiguous, cant change a(its final)
        System.out.println("Mahindra honksssss");
    }
}

class MultipleInheritance {
    public static void main(String[] args){
        Mahindra mh = new Mahindra();
        mh.honk();
    }
}