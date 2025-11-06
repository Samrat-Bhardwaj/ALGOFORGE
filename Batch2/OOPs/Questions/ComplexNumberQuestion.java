class ComplexNumber {
    int real;
    int imaginary;

    public ComplexNumber(int real, int imaginary){
        this.real = real;
        this.imaginary = imaginary;
    }

    public void add(ComplexNumber other){
        this.real += other.real;
        this.imaginary += other.imaginary;
    }

    public void subtract(ComplexNumber other){
        this.real -= other.real;
        this.imaginary -= other.imaginary;
    }

    public void multiply(ComplexNumber other){
        int totalReal = this.real * other.real - this.imaginary * other.imaginary;
        int totalImaginary = this.real * other.imaginary + other.imaginary * this.real;

        this.real = totalReal;
        this.imaginary = totalImaginary;
    }

    public String toString(){
        return this.real + " + " + this.imaginary + "i";
    }
}


class ComplexNumberQuestion {
    public static void main(String args[]){
        ComplexNumber c1 = new ComplexNumber(2,3);
        ComplexNumber c2 = new ComplexNumber(7,5);

        c1.add(c2);
        System.out.println(c1); // 9 + 8i
        System.out.println(c2); // 7 + 5i

        c2.multiply(c1); // (7 + 5i) * (9+8i);
        System.out.println(c2);
    }
}