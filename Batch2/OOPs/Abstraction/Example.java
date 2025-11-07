interface Shape {
    int a = 65;
    public void draw();
    public void area();
}

class Circle implements Shape {
    public void draw(){
        System.out.println("Drawing circle");
    }

    public void area(){
        System.out.println("Area circle");
    }
}

class Rectangle implements Shape {
    public void draw(){
        System.out.println("Drawing Rectangle");
    }

    public void area(){
        System.out.println("Area Rectangle");
    }
}

class Example {
    public static void main(String[] args){
        Shape s = new Circle();

        s.area();

        s = new Rectangle();
        s.area();
    }
}