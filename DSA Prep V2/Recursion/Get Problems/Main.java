import java.util.*;
class Main {
    // Get all subsequences
    public static ArrayList<String> getSubsequences(String str){
        if(str.length() == 0){
            ArrayList<String> bans = new ArrayList<>();
            bans.add("");
            return bans;
        }

        char firstChar = str.charAt(0);
        String smallerString = str.substring(1);

        ArrayList<String> smallerAns = getSubsequences(smallerString);

        ArrayList<String> ans = new ArrayList<>();

        // first character said no
        for(String sub: smallerAns){
            ans.add(sub);
        }

        // first character said yes
        for(String sub: smallerAns){
            ans.add(firstChar + sub);
        }

        return ans;
    }

    // Get keypad combinations
    static String[] keys = {";#,","abc","def","ghi","jkl","mno","pqr","stu","vwx","yz"};
    public static ArrayList<String> getKPC(String str){
        if(str.length() == 0){
            ArrayList<String> bans = new ArrayList<>();
            bans.add("");
            return bans;
        }

        char firstChar = str.charAt(0);
        String smallerString = str.substring(1);

        ArrayList<String> smallerAns = getKPC(smallerString);
        
        ArrayList<String> ans = new ArrayList<>();
        
        int firstNum = firstChar - '0'; 
        String key = keys[firstNum];

        for(int i=0; i<key.length(); i++){
            char keyChar = key.charAt(i);

            for(String sans: smallerAns){
                ans.add(keyChar + sans);
            }
        }

        return ans;
    }

    public static ArrayList<String> getStairPaths(int n){
        if(n < 0){
            ArrayList<String> bans = new ArrayList<>(); // no paths for negative steps
            return bans;
        }

        if(n == 0){
            ArrayList<String> bans = new ArrayList<>(); // one path which is do nothing, empty string
            bans.add("");
            return bans;
        }

        ArrayList<String> oneStepPaths = getStairPaths(n-1);
        ArrayList<String> twoStepPaths = getStairPaths(n-2);
        ArrayList<String> threeStepPaths = getStairPaths(n-3);

        ArrayList<String> allPaths = new ArrayList<>();

        // paths after taking 1 step
        for(String path: oneStepPaths){
            allPaths.add("1" + path);
        }

        // paths after taking 2 step
        for(String path: twoStepPaths){
            allPaths.add("2" + path);
        }

        // paths after taking 3 step
        for(String path: threeStepPaths){
            allPaths.add("3" + path);
        }

        return allPaths;
    }

    public static void main(String[] args){
        // System.out.println(getKPC("789"));
        System.out.println(getStairPaths(10));
    }
}