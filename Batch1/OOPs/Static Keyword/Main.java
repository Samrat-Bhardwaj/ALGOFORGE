class Bike {
    String engineType;
    String color;
    int horsePower;
    static String companyName = "Yamaha";
    static int numberOfBikes = 0;

    public Bike(String engineType, String color, int horsePower){
        this.engineType = engineType;
        this.color = color;
        this.horsePower = horsePower;
        this.numberOfBikes++;
    }

    public static int getNumberOfBikes(){
        return numberOfBikes;
    }

    public void noChange(){
        System.out.println("Making no change");
        makeSomeChange();
    }

    public static void makeSomeChange(){
        System.out.println("Making some change");
        // noChange();
    }
}
class Main {
    public static void createXBikes(int x){
        while(x-- > 0){
            Bike b = new Bike("A","Black", 100);
        }
    }
    public static void main(String[] args){
        // Bike.makeSomeChange();
        Bike b1 = new Bike("V8", "Red", 200);
        // b1.noChange();
        Bike b2 = new Bike("V7", "Blue", 300);

        // System.out.println(b1.companyName);
        // b1.companyName = "Yezdi";

        // System.out.println(b2.companyName);
        createXBikes(17);
        
        System.out.println(b1.getNumberOfBikes());
    }
}