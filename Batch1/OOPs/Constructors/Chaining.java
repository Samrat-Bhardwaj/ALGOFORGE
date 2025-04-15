class Vechile {
    String color;
    int mileage;
    int seats;
    int wheels;

    public void honk(){
        System.out.println("Ppppppppppp");
    }

    public Vechile(String color){
        this.color = color;
        System.out.println("Constructor with color is called");
    }

    public Vechile(String color, int mileage){
        this(color);
        this.mileage = mileage;
        System.out.println("Constructor with color and mileage is called");
    }

    public Vechile(String color, int mileage, int seats){
        this(color, mileage);
        this.seats = seats;
        System.out.println("Constructor with color, mileage,seats is called");
    }

    public Vechile(String color, int mileage, int seats, int wheels){
        this(color, mileage, seats);
        this.wheels = wheels;
        System.out.println("Constructor with color, mileage,seats and tires is called");
    }
}

class Chaining {
    public static void main(String[] args){
        Vechile v1 = new Vechile("Blue", 15, 4, 5);
        System.out.println(v1.color);
        System.out.println(v1.seats);
        System.out.println(v1.wheels);
        System.out.println(v1.mileage);
    }
}