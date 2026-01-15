import java.util.PriorityQueue;
import java.util.Comparator;
class Student implements Comparable {
    String name;
    int roll_no;
    int marks;
    int height;

    public Student(String name, int roll_no, int marks, int height){
        this.name = name;
        this.roll_no = roll_no;
        this.marks = marks;
        this.height = height;
    }

    @Override
    public int compareTo(Object other){ // negative (this), positive (other) or zero (random)
        Student ot = (Student)(other);
        return this.marks - ot.marks; // -> minPQ
    }

    public String toString(){
        return this.name + ": " + this.roll_no + "," + this.marks + "," + this.height;
    }
}

class StudentRollComparator implements Comparator<Student> {
    public int compare(Student t, Student o){
        return t.roll_no - o.roll_no;
    }
}

class StudentHeightComparator implements Comparator<Student> {
    public int compare(Student thiss, Student other){
        return other.height - thiss.height;
    }
}

class ComparatorIntro {
    public static void main(String[] args){
        // PriorityQueue<Student> pq = new PriorityQueue<>(new StudentRollComparator());
        PriorityQueue<Student> pq = new PriorityQueue<>((Student thiss, Student other) -> { // lambda function
            return other.roll_no - thiss.roll_no;
        });

        pq.add(new Student("Samrat", 1, 100, 56));
        pq.add(new Student("Ajay", 3, 10, 23));
        pq.add(new Student("Hemant", 2, 0, 97));
        
        while(pq.size() > 0){
            System.out.println(pq.remove());
        }
    }
}