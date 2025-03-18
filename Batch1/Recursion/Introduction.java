class Introduction {
    public static void swap(int a, int b){
        int temp = a;
        
        a = b;
        b = temp;
    }
    
    public static void main(String[] args){
        int a = 5;
        int b = 7;

        System.out.println(a + " " + b);

        swap(a,b);

        System.out.println(a + " " + b);
    }
}