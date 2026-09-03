class Student {
    String name;
    int roll_number;
    int age;

    // default constructor
    public Student(){
    }

    public Student(String name, int roll_number){
        this.name = name;
        this.roll_number = roll_number;
    }

    public int sum(){
        int roll_number = 104;
        String name = "HEY";
        System.out.println(this.name);
        return this.roll_number;
    }
}

class Example {
    public static void main(String[] args){
        Student s1 = new Student(); // object "s1" of class "Student"
        Student s2 = new Student("Aman", 24);   
        
        System.out.println(s2.name);
    }
}