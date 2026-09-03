class Student {
    String name;
    int roll_number;
    int age;

    // default constructor
    public Student(){
        System.out.println("Default constructor is called");
    }

    public Student(String name, int roll_number){
        this.name = name;
        this.roll_number = roll_number;
        System.out.println("Parametrized constructor is called");
    }

    public Student(String name, int roll_number, int age){
        this.name = name;
        this.roll_number = roll_number;
        this.age = age;
        System.out.println("Parametrized constructor 2 is called");
    }
}

class Main {
    public static void main(String[] args){
        Student s1 = new Student(); // object "s1" of class "Student"
        Student s2 = new Student("Aman", 24);   
        Student s3 = new Student("Shubham", 44, 50);   

        System.out.println(s1.name + "," + s2.roll_number + "," + s3.age);
    }
}