class Main {
    public static void main(String[] args){
        String s1 = "hello";
        String s2 = "hello";
        String s3 = new String("hello");

        if(s1 == s2){
            System.out.println("s1 and s2 is equal");
        }

        if(s1 == s3){ // only checks address
            System.out.println("s1 and s3 is equal part 1");
        }

        if(s1.equals(s3)){ // first address check, for diff add, it will check characters
            System.out.println("s1 and s3 is equal part 2");
        }
    }
}