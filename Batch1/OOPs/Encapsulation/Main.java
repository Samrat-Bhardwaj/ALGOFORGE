class Employee {
    private int salary;
    int joiningyear;
    int employeeId;

    // getter function
    public int getSalary(String password){
        if(password.equals("ABCD")){
            return this.salary;
        } else {
            System.out.println("You can't access this");
            return -1;
        }
        
    }

    // setter function
    public void setSalary(int newSalary){
        if(newSalary > 100000){
            this.salary = -100;
        } else {
            this.salary = newSalary;
        }
    }
}

class Main {
    public static void main(String[] args){
        Employee e1 = new Employee();
        e1.setSalary(1006700);
        // e1.salary = 7676767;

        System.out.println(e1.getSalary("QWERTY"));
    }
}