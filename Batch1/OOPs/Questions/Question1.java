class Bike {
    String engineType;
    String color;
    int horsePower;

    public Bike(String engineType, String color, int horsePower){
        this.engineType = engineType;
        this.color = color;
        this.horsePower = horsePower;
    }

    public void colorChange(String newColor){
        this.color = newColor;
    }

    public void doubleHorsepower(){
        this.horsePower = 2*this.horsePower;
    }
}
class Question1 {
    public static void main(String[] args){
        Bike b = new Bike("V8", "Red", 200);

        System.out.println(b.engineType);
        System.out.println(b.color);
        System.out.println(b.horsePower);

        b.colorChange("Blue");

        System.out.println(b.engineType);
        System.out.println(b.color);
        System.out.println(b.horsePower);

        b.doubleHorsepower();
        System.out.println(b.horsePower);

        b.doubleHorsepower();
        System.out.println(b.horsePower);

    }
}