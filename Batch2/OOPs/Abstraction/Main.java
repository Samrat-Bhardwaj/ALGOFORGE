abstract class Shape {
    int numberOfSides;

    abstract public double getArea();
    public void print(){
        System.out.println("Printing");
    }
}

class Rectangle extends Shape {
    int length;
    int width;
    public Rectangle(int length, int width){
        super.numberOfSides = 4;
        this.length = length;
        this.width = width;
    }
    public double getArea(){
        return this.length*this.width;
    }
}

class Circle extends Shape{
    int radius;

    public Circle(int radius){
        super.numberOfSides = 0;
        this.radius = radius;
    }

    public double getArea(){
        return 3.14*this.radius*this.radius;
    }
}

class Main {
    public static void main(String[] args){
        Circle s = new Circle(4);
        System.out.println(s.getArea());
        s.print();
    }
}