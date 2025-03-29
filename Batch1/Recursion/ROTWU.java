class ROTWU {
    // ===================  RECURSION ON THE WAY UP ====================================================

    public static void printSubsequences(String str, String asf){
        if(str.length() == 0){
            System.out.println(asf);
            return;
        }

        char firstChar = str.charAt(0);
        String remString = str.substring(1);

        // first character said no
        printSubsequences(remString, asf);

        // first character said yes
        printSubsequences(remString, asf + firstChar);        
    }

    static String[] lettersArray = {",:","<;","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
    public static void printKPC(String str, String asf){
        if(str.length() == 0){
            System.out.println(asf);
            return;
        }

        String remString = str.substring(1);
        int firstChar = str.charAt(0) - '0';

        String letters = lettersArray[firstChar];

        for(int i=0; i<letters.length(); i++){
            char letter = letters.charAt(i);

            printKPC(remString, asf + letter);
        }
    }



    public static void printAllStairPaths(int n, String asf){
        if(n<0){
            return;
        }

        if(n==0){
            System.out.println(asf);
            return;
        }


        printAllStairPaths(n-1, asf + "1");
        printAllStairPaths(n-2, asf + "2");
        printAllStairPaths(n-3, asf + "3");
    }


    // 0,0,n-1,m-1
    public static void printAllMazePathsWithJumps(int row, int col, int dr, int dc, String psf){ // path so far
        if(row == dr && col == dc){
            System.out.print(psf+" , ");
            return;
        }

        // horizontal moves 
        for(int jump=1; jump <= dc-col; jump++){
            printAllMazePathsWithJumps(row, col+jump, dr, dc, psf + "h" + jump);
        }

        // vertical moves
        for(int jump=1; jump <= dr-row; jump++){
            printAllMazePathsWithJumps(row+jump, col, dr, dc, psf + "v" + jump);
        }

        // diagnoal moves
        for(int jump=1; jump<= Math.min(dr-row, dc-col); jump++){
            printAllMazePathsWithJumps(row+jump, col+jump, dr,dc, psf+"d"+jump);
        }
    }

    public static void main(String[] args){
        // printAllMazePathsWithJumps(0,0,2,2, "");
        printEncodings("303","");
    }
}