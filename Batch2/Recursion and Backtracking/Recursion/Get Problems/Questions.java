import java.util.ArrayList;
class Questions {

    public static ArrayList<String> getSubsequences(String str){
        if(str.length() == 0){
            ArrayList<String> baseAns = new ArrayList<>();
            baseAns.add("");
            return baseAns;
        }

        String smallerString = str.substring(1); // "bc"

        ArrayList<String> smallerAns = getSubsequences(smallerString);

        char firstChar = str.charAt(0);

        ArrayList<String> myAns = new ArrayList<>();
        // 'a' will say no
        for(String s: smallerAns){ // for(int i=0; i<smallerAns.size(); i++) String s = smallerAns.get(i)
            myAns.add(s);
        }

        // 'a' will say yes
        for(String s: smallerAns){ // for(int i=0; i<smallerAns.size(); i++) String s = smallerAns.get(i)
            myAns.add(firstChar + s);
        }

        return myAns;
    }

    static String[] lettersArray = {",:","<;","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
    public static ArrayList<String> getKeypadCombinations(String str){
        if(str.length() == 0){
            ArrayList<String> baseAns = new ArrayList<>();
            baseAns.add("");
            return baseAns;
        }

        String smallerString = str.substring(1);
        ArrayList<String> smallerAns = getKeypadCombinations(smallerString);

        ArrayList<String> myAns = new ArrayList<>();

        int firstDigit = str.charAt(0) - '0'; // '8' - '0' = 8;
        String letters = lettersArray[firstDigit]; 

        for(int i=0; i<letters.length(); i++){
            char letter = letters.charAt(i);

            for(String s: smallerAns){
                myAns.add(letter + s);
            }
        }

        return myAns;
    }

    public static ArrayList<String> getStairPaths(int n){
        if(n < 0){
            ArrayList<String> baseAns = new ArrayList<>();
            return baseAns;
        }

        if(n == 0){
            ArrayList<String> baseAns = new ArrayList<>();
            baseAns.add("");
            return baseAns;
        }

        ArrayList<String> pathsAfterOneStep = getStairPaths(n-1);
        ArrayList<String> pathsAfterTwoStep = getStairPaths(n-2);
        ArrayList<String> pathsAfterThreeStep = getStairPaths(n-3);

        ArrayList<String> allPaths = new ArrayList<>();

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

    public static ArrayList<String> getMazePaths(int sr, int sc, int dr, int dc){
        if(sr > dr || sc > dc){
            return new ArrayList<>();
        }
        if(sr == dr && sc == dc){
            ArrayList<String> baseAns = new ArrayList<>();
            baseAns.add("");
            return baseAns;
        }

        ArrayList<String> pathsAfterHStep = getMazePaths(sr,sc+1,dr,dc);
        ArrayList<String> pathsAfterVStep = getMazePaths(sr+1,sc,dr,dc);

        ArrayList<String> allPaths = new ArrayList<>();

        for(String path: pathsAfterHStep){
            allPaths.add("h" + path);
        }

        for(String path: pathsAfterVStep){
            allPaths.add("v" + path);
        }

        return allPaths;
    }

    public static ArrayList<String> getMazePathsWithJumps(int sr, int sc, int dr, int dc){
        if(sr == dr && sc == dc){
            ArrayList<String> baseAns = new ArrayList<>();
            baseAns.add("");
            return baseAns;
        }

        ArrayList<String> allPaths = new ArrayList<>();

        // horizontal jumps
        for(int jump = 1; jump <= dc-sc; jump++){
            ArrayList<String> pathsAfterHJump = getMazePathsWithJumps(sr, sc+jump, dr, dc);

            for(String path: pathsAfterHJump){
                allPaths.add("h" + jump + path);
            }
        }

        // vertical jumps
        for(int jump = 1; jump <= dr-sr; jump++){
            ArrayList<String> pathsAfterVJump = getMazePathsWithJumps(sr+jump, sc, dr, dc);

            for(String path: pathsAfterVJump){
                allPaths.add("v" + jump + path);
            }
        }

        return allPaths;
    }

    public static void main(String[] args){
        // ArrayList<String> ans = getSubsequences("abc");
        // ArrayList<String> ans = getKeypadCombinations("84");
        // ArrayList<String> ans = getStairPaths(4);
        // ArrayList<String> ans = getMazePaths(0,0,2,2);
        ArrayList<String> ans = getMazePathsWithJumps(0,0,2,2);

        System.out.println(ans);
    }
}