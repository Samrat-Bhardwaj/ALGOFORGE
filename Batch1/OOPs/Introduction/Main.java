class Student {
    String name;
    int roll_number;

    public void makeNoise(){
        System.out.println("Making noise!!!!!");
    }

    public void printSum(int a, int b){
        System.out.println(a+b);
    }

    public String toString(){
        return "Name of the student is: " + name  + ", and roll number is: " + roll_number;
    }
}

class Main {
    public static void main(String[] args){
        Student s1 = new Student();
        Student s2 = new Student();
        s1.name = "Ram";
        s1.roll_number = 1;
        s2.name = "Shyam";
        s2.roll_number = 21;

        // System.out.println(s2);
        // System.out.println(s1);
        System.out.println("Hello AlgoForge");

        // s1.printSum(21,43);
    }
}