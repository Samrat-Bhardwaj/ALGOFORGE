import java.util.PriorityQueue;
class Student implements Comparable {
    String name;
    int roll_no;
    int marks;

    public Student(String name, int roll_no, int marks){
        this.name = name;
        this.roll_no = roll_no;
        this.marks = marks;
    }

    @Override
    public int compareTo(Object other){ // negative (this), positive (other) or zero (random)
        Student ot = (Student)(other);
        return this.marks - ot.marks; // -> minPQ
    }

    public String toString(){
        return this.name + ": " + this.roll_no + "," + this.marks;
    }
}

class Main {
    public static void main(String[] args){
        PriorityQueue<Student> pq = new PriorityQueue<>();

        pq.add(new Student("Samrat", 1, 100));
        pq.add(new Student("Ajay", 3, 10));
        pq.add(new Student("Hemant", 2, 0));
        
        while(pq.size() > 0){
            System.out.println(pq.remove());
        }
    }
}