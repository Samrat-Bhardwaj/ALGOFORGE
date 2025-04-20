class ComplexNumber {
    int real;
    int imaginary;

    public ComplexNumber(int real, int imaginary){
        this.real = real;
        this.imaginary = imaginary;
    }

    public void add(ComplexNumber c2){
        this.real = this.real + c2.real;
        this.imaginary = this.imaginary + c2.imaginary;
    }

    public void multiply(ComplexNumber c2){
        this.real = this.real*c2.real - (this.imaginary*c2.imaginary);
        this.imaginary = this.real*c2.imaginary + this.imaginary*c2.real;
    }

    public ComplexNumber addTwoComplexNumbers(ComplexNumber c1, ComplexNumber c2){
        ComplexNumber c3 = new ComplexNumber(c1.real+c2.real, c1.imaginary+c2.imaginary);

        return c3;
    }

    public String toString(){
        return this.real + " + " + this.imaginary +"i";
    }
}


class ComplexNumberQuestion {
    public static ComplexNumber addTwoComplexNumbers(ComplexNumber c1, ComplexNumber c2){
        ComplexNumber c3 = new ComplexNumber(c1.real+c2.real, c1.imaginary+c2.imaginary);

        return c3;
    }

    public static void main(String[] args){
        ComplexNumber c1 = new ComplexNumber(5,3); // 5 + 3i
        ComplexNumber c2 = new ComplexNumber(4,2);

        System.out.println(c1);
        System.out.println(c2);

        c1.add(c2); // c1 = 9 + 5i
        // c2.add(c1);
        ComplexNumber ans = addTwoComplexNumbers(c1,c2);

        System.out.println(c1);
        System.out.println(c2);
        System.out.println(ans);
    }
}