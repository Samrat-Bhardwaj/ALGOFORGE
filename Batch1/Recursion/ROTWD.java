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


    // sr -> source row
    // sc -> source col
    // dr -> destination row
    // dc -> destination col

    // allMazePaths(0,0,n-1,m-1);
    public static ArrayList<String> allMazePaths(int sr, int sc, int dr, int dc){
        if(sr > dr || sc > dc){ // wrong place 
            return new ArrayList<>();
        }

        if(sr == dr && sc == dc){
            ArrayList<String> baseAns = new ArrayList();
            baseAns.add("");
            return baseAns;
        }


        ArrayList<String> hpaths = allMazePaths(sr, sc+1, dr, dc);
        ArrayList<String> vpaths = allMazePaths(sr+1, sc, dr, dc);

        ArrayList<String> allPaths = new ArrayList<>();

        for(String path: hpaths){
            allPaths.add("h" + path);
        }

        for(String path: vpaths){
            allPaths.add("v" + path);
        }

        return allPaths;
    }

    public static ArrayList<String> getAllMazePathsWithJumps(int sr, int sc, int dr, int dc){
        if(sr == dr && sc == dc){
            ArrayList<String> baseAns = new ArrayList<>();
            baseAns.add("");
            return baseAns;
        }

        ArrayList<String> allPaths = new ArrayList<>();
        // horizontal moves
        for(int jump = 1; jump <= dc-sc; jump++){
            ArrayList<String> hpaths = getAllMazePathsWithJumps(sr, sc+jump, dr, dc);

            for(String path: hpaths){
                allPaths.add("h" + jump + path);
            }
        }
        // vertical moves
        for(int jump=1; jump <= dr-sr; jump++){
            ArrayList<String> vpaths = getAllMazePathsWithJumps(sr+jump, sc, dr, dc);

            for(String path: vpaths){
                allPaths.add("v" + jump + path);
            }
        }
        // diagonal moves
        for(int jump=1; jump <= Math.min(dc-sc,dr-sr); jump++){
            ArrayList<String> dpaths = getAllMazePathsWithJumps(sr+jump, sc+jump, dr, dc);

            for(String path: dpaths){
                allPaths.add("d" + jump + path);
            }
        }

        return allPaths;
    }

    public static void main(String[] args){
        ArrayList<String> ans = getAllMazePathsWithJumps(0,0,2,2);

        System.out.println(ans); // [,c,b,bc,a,ac,ab,abc]
    }
}