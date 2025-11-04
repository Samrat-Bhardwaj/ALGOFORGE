class Student {
    String name;
    int roll_number;
    int age;

    // default constructor
    public Student(){
        System.out.println("Default constructor is called");
    }

    public Student(String name){
        this.name = name;
        System.out.println("Constructor setting name is called");
    }

    public Student(String name, int roll_number){
        this(name);
        this.roll_number = roll_number;
        System.out.println("Constructor setting name, roll_number is called");
    }

    public Student(String name, int roll_number, int age){
        this(name,roll_number);
        this.age = age;
        System.out.println("Constructor setting name, roll_number and age is called");
    }
}

class ConstructorChaining {
    public static void main(String[] args){ 
        Student s3 = new Student("Shubham", 44, 50);
    }
}