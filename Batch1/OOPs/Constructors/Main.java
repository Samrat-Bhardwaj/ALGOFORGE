class Student {
    String name;
    int roll_number = -1;

    public Student(){
        name = "AlgoForge";
        roll_number = -189;
        System.out.println("Student default constructor called");
    }

    public Student(String name){
        this.name = name;
        System.out.println("Constructor with 1 Parameter called");
    }

    public Student(String name, int roll_number){
        this.name = name;
        this.roll_number = this.roll_number;
        System.out.println("Constructor with 2 Parameter called");
    }
}

class Main {
    public static void main(String[] args){
        Student s1 = new Student();
        Student s2 = new Student("Shyam");
        Student s3 = new Student("Pushan", 276);

        System.out.println(s3.roll_number + ", " + s3.name);
    }
}