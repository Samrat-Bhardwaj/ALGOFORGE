class Car {
    String engineType;
    String color;
    int horsePower;

    public Car(){}

    public Car(String engineType){
        this.engineType = engineType;
    }

    public Car(String engineType, String color){
        this(engineType);
        this.color = color;
    }

    public Car(String engineType, String color, int horsePower){
        this(engineType, color);
        this.horsePower = horsePower;
    }

    public void doubeHorsePower(){
        this.horsePower *= 2;
    }

    public void changeColor(String newColor){
        this.color = newColor;
    }

    public String toString(){
        return "Engine type is " + this.engineType + " ,color is " + this.color + " and horsepower is " + this.horsePower;
    }
}

class CarQuestion {
    public static void main(String[] args){
        Car c1 = new Car("V8", "Black", 800);
        Car c2 = new Car();
        // System.out.println(c1.color + "," + c1.engineType + "," + c1.horsePower);

        // c1.doubeHorsePower();

        // System.out.println(c1.color + "," + c1.engineType + "," + c1.horsePower);

        // c1.doubeHorsePower();
        // c1.changeColor("StarDust");

        // System.out.println(c1.color + "," + c1.engineType + "," + c1.horsePower);
        System.out.println(c1);
        // System.out.println(c2);
    }
}