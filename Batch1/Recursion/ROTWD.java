// Recursion on the way down
import java.util.*;
class ROTWD {

    public static ArrayList<String> getSubsequences(String str){
        if(str.length() == 0){
            ArrayList<String> baseAns = new ArrayList<>();
            baseAns.add("");
            return baseAns;
        }

        char firstChar = str.charAt(0);
        String remString = str.substring(1); // abc -> bc

        ArrayList<String> smallerAns = getSubsequences(remString); // 

        ArrayList<String> myAns = new ArrayList<>();

        // 'a' said no
        for(String s: smallerAns){
            myAns.add(s);
        }

        // 'a' said yes
        for(String s: smallerAns){
            myAns.add(firstChar + s);
        }

        return myAns;
    }

    static String[] lettersArray = {",:","<;","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
    public static ArrayList<String> getKPC(String str){ // "456"
        if(str.length() == 0){
            ArrayList<String> baseAns = new ArrayList<>();
            baseAns.add("");
            return baseAns;
        }
        
        String remString = str.substring(1);
        int firstDigit = str.charAt(0) - '0'; // '4' - '0' = 4;

        ArrayList<String> smallerAns = getKPC(remString);
        ArrayList<String> myAns = new ArrayList<>();

        String letters = lettersArray[firstDigit];

        for(int i=0; i<letters.length(); i++){
            char letter = letters.charAt(i); 

            for(String s: smallerAns){
                myAns.add(letter + s);
            }
        }

        return myAns;
    }

    public static ArrayList<String> getAllStairPaths(int n){
        if(n<0){
            return new ArrayList<>();
        }

        if(n==0){
            ArrayList<String> baseAns = new ArrayList<>();
            baseAns.add("");
            return baseAns;
        }

        ArrayList<String> allPaths = new ArrayList<>();

        ArrayList<String> pathsAfterOneStep = getAllStairPaths(n-1);
        ArrayList<String> pathsAfterTwoStep = getAllStairPaths(n-2);
        ArrayList<String> pathsAfterThreeStep = getAllStairPaths(n-3);

        for(String path: pathsAfterOneStep){
            allPaths.add("1" + path);
        }

        for(String path: pathsAfterTwoStep){
            allPaths.add("2" + path);
        }

        for(String path: pathsAfterThreeStep){
            allPaths.add("3" + path);
        }

        return allPaths;
    }

























    public static void main(String[] args){
        ArrayList<String> ans = getAllStairPaths(10);

        System.out.println(ans); // [,c,b,bc,a,ac,ab,abc]
    }
}