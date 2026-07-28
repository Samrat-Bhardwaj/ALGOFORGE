import java.util.Scanner;
class Questions {
    public static String compressString(String str){
        String res = "";
        res += str.charAt(0);

        for(int i=1; i<str.length(); i++){
            if(str.charAt(i) !=  str.charAt(i-1)){
                res += str.charAt(i);
            }
        }

        return res;
    }

    public static String compressString2(String str){
        
    }

    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);

        String str = scn.next();

        System.out.println(compressString(str));
    }
}