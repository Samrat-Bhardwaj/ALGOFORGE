interface Shape {
    abstract public double getArea();
} 

class Rectangle implements Shape {
    int length;
    int width;

    public Rectangle(int length, int width){
        this.length = length;
        this.width = width;
    }

    public double getArea(){
        return length*width;
    }
}

class Circle implements Shape {
    int radius;

    public Circle(int radius){
        this.radius = radius;
    }

    public double getArea(){
        return this.radius*this.radius*3.14;
    }
}

class InterfaceExample{
    public static void main(String[] args){
        Circle c1 = new Circle(2);
        System.out.println(c1.getArea());

        Rectangle r1 = new Rectangle(2,3);
        System.out.println(r1.getArea());
    }
}