class Car {
    String engineType;
    String color;
    int horsePower;

    static String companyName = "Hyundai"; 

    public void horn(){
        System.out.println("Pressing horn");
    }

    public static void makeNoise(){
        color = "red";
        System.out.println("Making Noise");
    }
}

class Main {
    public static void main(String[] args){
        Car.makeNoise();
    }
}