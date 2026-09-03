abstract class Shape {
    int numberOfSides;
    abstract public double area();

    public void printSides(){
        System.out.println(this.numberOfSides);
    }
}

class Rectangle extends Shape {
    int length;
    int width;

    public Rectangle(int length, int width){
        this.numberOfSides = 4;
        this.length = length;
        this.width = width;
    }

    public double area(){
        return length*width;
    }
}

class Circle extends Shape {
    int radius;

    public Circle(int radius){
        this.numberOfSides = 0;
        this.radius = radius;
    }

    public double area(){
        return this.radius*this.radius*3.14;
    }
}

class Example {
    public static void main(String[] args){
        Circle c1 = new Circle(2);
        System.out.println(c1.area());

        Rectangle r1 = new Rectangle(2,3);
        System.out.println(r1.area());
        
    }
}