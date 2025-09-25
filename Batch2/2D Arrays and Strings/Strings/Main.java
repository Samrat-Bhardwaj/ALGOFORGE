class Main {
    public static void main(String[] args){
        // String str = "AlgoForge";
        // str = str + 234;

        // System.out.println(str.charAt(4));

        String s1 = "hello";
        String s2 = "hello";
        String s3 = new String("hello"); // new address 

        if(s1 == s3){ // O(1)
            System.out.println("Both the strings are equal");
        } else {
            System.out.println("Both the strings are not equal");
        }

        if(s1.equals(s3)){ // first it will check addresses, if addresses are not equal, character by character check
            System.out.println("Both the strings are equal part 2");
        }
    }
}