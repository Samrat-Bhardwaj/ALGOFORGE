class ComplexNumber {
    int real;
    int imaginary;
}


class ComplexNumberQuestion {
    public static void main(String args[]){
        ComplexNumber c1 = new ComplexNumber(2,3);
        ComplexNumber c2 = new ComplexNumber(7,5);

        c1.add(c2);
        System.out.println(c1); // 9 + 8i
        System.out.println(c2); // 7 + 5i
    }
}