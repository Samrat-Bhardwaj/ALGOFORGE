class Interning {
    public static void main(String[] args){
        String s1 = "hello";
        String s2 = "hello";

        String s3 = new String("hello");

        if(s1.equals(s3) == true){
            System.out.println("Equal!!");
        } else {
            System.out.println("Not Equal!!");
        }

        String s = "hello";

        for(int i=0; i<100; i++){ // 100 -> 100^2
            s += "x"; 
        }
    }
}