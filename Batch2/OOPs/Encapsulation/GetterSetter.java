class Student {
    String name;
    int roll_number;
    private int marks;
    private String adminPassword = "password";

    public int getMarks(){
        // can add authentication
        return this.marks;
    }

    public void setMarks(int marks, String password){
        if(marks < 0){
            System.out.println("Marks can't be less than 0");
        }
        
        if(password.equals(adminPassword)){
            this.marks = marks;
        } else {
            System.out.println("Password didn't match, try again!!");
        }
    }
}


class GetterSetter {
    public static void main(String[] args){
        Student s = new Student();

        s.setMarks(45,"password");
        System.out.println(s.getMarks());

        s.setMarks(45,"WrongPassword");
    }  
}