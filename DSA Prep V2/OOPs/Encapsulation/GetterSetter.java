class Student {
    String name;
    protected int rollNumber;
    private int marks = 50;
    final private String adminPassword = "password";

    public Student(String name, int rollNumber){
        this.name = name;
        this.rollNumber = rollNumber;
    }

    // getter function
    public int getMarks(){
        return this.marks;
    }

    // setter function
    public void setMarks(String password, int newMarks){
        if(password.equals(adminPassword)){
            this.marks = newMarks;
        } else {
            System.out.println("Wrong password!!!");
        }
    }
}


class GetterSetter {
    public static void main(String[] args){
        Student s1 = new Student("Algo", 1);
        // s1.marks = 100;
        s1.setMarks("passwod", 99);
        System.out.println(s1.getMarks());
    }
}