class Student {
    String name;
    int roll_number;
    int age;

    public void makeNoise(){
        System.out.println("Students are not making noise");
    }
}

class Main {
    public static void main(String[] args){
        Student s1 = new Student(); // object "s1" of class "Student"
        Student s2 = new Student(); // object "s1" of class "Student"

        s1.name = "AlgoForge";
        s1.roll_number = 55;
        s1.age = 23;

        System.out.println(s1.name);
        System.out.println(s1.roll_number);
        System.out.println(s2.age);
        s1.makeNoise();
    }
}