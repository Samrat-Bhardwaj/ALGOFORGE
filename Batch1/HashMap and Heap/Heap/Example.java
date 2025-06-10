import java.util.PriorityQueue;
class Vehicle implements Comparable {
    int mileage;
    int number_of_wheels;
    String color;

    public Vehicle(int mileage, int number_of_wheels, String color){
        this.mileage = mileage;
        this.number_of_wheels = number_of_wheels;
        this.color = color;
    }

    @Override
    public int compareTo(Object ot){
        Vehicle other = (Vehicle)(ot);
        // return this.mileage - other.mileage; // 20 - 220 => -200
        return other.mileage - this.mileage; // 220 - 2220 => -2000
    }

    @Override
    public String toString(){
        return "(Mileage: " + this.mileage + ", number_of_wheels: " + this.number_of_wheels + ", Color: "  + this.color + ")";
    }
}

class Example {
    public static void main(String[] args){
        PriorityQueue<Vehicle> pq = new PriorityQueue<>();

        pq.add(new Vehicle(100,2,"Red"));
        pq.add(new Vehicle(1300,3,"Blue"));
        pq.add(new Vehicle(200,1,"Yellow"));
        pq.add(new Vehicle(500,19,"Baigani"));

        Vehicle child = new Vehicle(2220,1,"A");
        Vehicle parent = new Vehicle(220,11,"B");

        child.compareTo(parent);


        while(pq.size() > 0){
            System.out.println(pq.remove());
        }
    }
}