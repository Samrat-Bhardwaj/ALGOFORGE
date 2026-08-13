class Main {
    public static void printSubsequences(String str, String asf){ // answer so far
        if(str.length() == 0){
            System.out.println(asf);
            return;
        }

        char firstChar = str.charAt(0);

        String smallerString = str.substring(1);

        printSubsequences(smallerString, asf + firstChar); // firstChar said yes
        printSubsequences(smallerString, asf);  // firstChar said no
    }

    static String[] keys = {";#,","abc","def","ghi","jkl","mno","pqr","stu","vwx","yz"};
    public static void printKPC(String str, String asf){
        if(str.length() == 0){
            System.out.print(asf + ",");
            return;
        }

        char firstChar = str.charAt(0);
        String smallerString = str.substring(1);

        int firstNum = firstChar - '0';
        String key = keys[firstNum];

        for(int i=0; i<key.length(); i++){
            char keyChar = key.charAt(i);
            printKPC(smallerString, asf + keyChar);
        }
    }

    public static void printStairPaths(int n, String psf){ // path so far
        if(n < 0){ // no path to take negative steps
            return;
        }

        if(n == 0){
            System.out.print(psf + ", ");
            return;
        }
        
        printStairPaths(n-1, psf + "1");
        printStairPaths(n-2, psf + "2");
        printStairPaths(n-3, psf + "3");
    }

    public static void printMazePaths(int sr, int sc, int dr, int dc, String psf){
        if(sr > dr || sc > dc){
            return;
        }

        if(sr == dr && sc == dc){
            System.out.print(psf + ", ");
            return;
        }

        printMazePaths(sr, sc + 1, dr, dc, psf + "h");
        printMazePaths(sr + 1, sc, dr, dc, psf + "v");
    }

    public static void main(String[] args){
        // printSubsequences("abc","");
        // printKPC("789","");
        // printStairPaths(4,"");
        printMazePaths(0,0,1,2,"");
    }
}