class Shape {
    int area;

    public Shape(){
        System.out.println("Constructor of class Shape is called");
    }
}

class Polygon extends Shape {
    int number_of_sides;

    public Polygon(){
        System.out.println("Constructor of class Polygon is called");
    }
}

class Rectangle extends Polygon{
    int length;
    int width;

    public Rectangle(){
        super.area = 4;
        System.out.println("Constructor of class Rectangle is called");
    }
}

class MultiLevelInheritance {
    public static void main(String[] args){
        Rectangle r = new Rectangle(); 
        r.area = 23;
        r.number_of_sides = 4;
        r.length = 3;
    }
}