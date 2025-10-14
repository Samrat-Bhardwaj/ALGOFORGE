class Questions {
    // question string, subsequence so far
    public static void printSubsequences(String str, String ssf){
        if(str.length() == 0){
            System.out.println(ssf);
            return;
        }

        char firstChar = str.charAt(0);
        String smallerString = str.substring(1);

        printSubsequences(smallerString, ssf); // firstChar said no
        printSubsequences(smallerString, ssf + firstChar); // firstChar said yes
    }

    static String[] lettersArray = {",:","<;","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
    public static void printKeypadCombinations(String str, String asf){
        if(str.length() == 0){
            System.out.println(asf);
            return;
        }

        String smallerString = str.substring(1);

        int firstDigit = str.charAt(0) - '0';
        String letters = lettersArray[firstDigit];

        for(int i=0; i<letters.length(); i++){
            char letter = letters.charAt(i);

            printKeypadCombinations(smallerString, asf + letter);
        }
    }

    public static void printStairPaths(int n, String psf){
        if(n < 0){
            return;
        }

        if(n == 0){
            System.out.println(psf);
            return;
        }

        printStairPaths(n-1, psf + "1");
        printStairPaths(n-2, psf + "2");
        printStairPaths(n-3, psf + "3");
    }

    // HW 
    public static void printMazePaths(int sr, int sc, int dr, int dc, String psf){

    }

    // All maze paths with horizontal, vertical and diagonal moves with infinite jumps allowed
    public static void printMazePathsWithJumps(int sr, int sc, int dr, int dc, String psf){
        if(sr == dr && sc == dc){
            System.out.println(psf);
            return;
        }

        // horizontal jumps
        for(int jump=1; jump <= dc-sc; jump++){
            printMazePathsWithJumps(sr, sc+jump, dr, dc, psf + "h" + jump);
        }

        //vertical jumps
        for(int jump=1; jump <= dr - sr; jump++){
            printMazePathsWithJumps(sr+jump,sc,dr,dc, psf + "v" + jump);
        }

        // diagonal jumps
        for(int jump=1; jump <= Math.min(dc-sc, dr-sr); jump++){
            printMazePathsWithJumps(sr+jump, sc+jump, dr, dc, psf + "d" + jump);
        }
    }

    // print Encodings
    public static char digitToChar(int digit){
        return (char)((digit - 1) + 'a');
    }

    public static void printEncodings(String str, String asf){
        if(str.length() == 0){
            System.out.println(asf);
            return;
        }

        int firstDigit = str.charAt(0) - '0';

        if(firstDigit == 0){ // no possible solution
            return;
        }

        char cChar = digitToChar(firstDigit);
        String smallerString = str.substring(1);

        printEncodings(smallerString, asf + cChar);

        if(str.length() >=2){
            String first2Chars = str.substring(0,2);
            int first2Digits = Integer.parseInt(first2Chars);

            if(first2Digits <= 26){
                cChar = digitToChar(first2Digits);
                smallerString = str.substring(2);
                printEncodings(smallerString, asf + cChar);
            } 
        }
    }

    public static void main(String[] args){
        // printSubsequences("abc","");
        // printKeypadCombinations("47","");
        // printStairPaths(4,"");
        // printMazePaths(0,0,2,2,"");
        // printMazePathsWithJumps(0,0,2,2,"");
        printEncodings("1234","");
    }
}