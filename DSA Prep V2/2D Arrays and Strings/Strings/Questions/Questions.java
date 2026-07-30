import java.util.*;
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
        String res = "" + str.charAt(0);
        int count = 1;

        for(int idx=1; idx<str.length(); idx++){
            if(str.charAt(idx) == str.charAt(idx - 1)){ // old character
                count++;
            } else { // new character
                if(count > 1){
                    res += count; // adding count of last char
                }

                res += str.charAt(idx); // adding new character, count of which will be added later
                count = 1; // re-initialising count with 1 for new character
            }
        }

        if(count > 1){
            res += count;
        }

        return res;
    }

    public static String toggleCase(String str){
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<str.length(); i++){
            char ch = str.charAt(i);

            if('a' <= ch && ch <= 'z'){
                char upperCase = (char)('A' + (ch - 'a'));
                sb.append(upperCase);
            } else if('A' <= ch && ch <= 'Z'){
                char lowerCase = (char)('a' + (ch - 'A'));
                sb.append(lowerCase);
            } else {
                sb.append(ch);
            }
        }

        return sb.toString();
    }

    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);

        String str = scn.next();

        System.out.println(toggleCase(str));
    }
}