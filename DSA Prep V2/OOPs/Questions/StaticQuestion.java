class Bike {
    String engineType;
    int horsePower;

    private static int totalBikeInstances = 0;

    public Bike(){
        totalBikeInstances++;
    }

    public static int getNumberOfBikes(){
        return totalBikeInstances;
    }
}

class StaticQuestion {
    public static void main(String[] args) {
        Bike b1 = new Bike();
        // b1.totalBikeInstances = 24; -> error: totalBikeInstances has private access in Bike
        
        Bike b2 = new Bike();
        Bike b3 = new Bike();
        System.out.println(Bike.getNumberOfBikes()); // 3
    }
}