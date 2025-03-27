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





























    public static void main(String[] args){
        printAllStairPaths(4, "");
    }
}