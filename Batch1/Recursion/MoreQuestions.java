class MoreQuestions {
    // print Encodings , 1234, 303
    public static void printEncodings(String str, String asf){
        if(str.length() == 0){
            System.out.println(asf);
            return;
        }

        if(str.charAt(0) == '0'){ // leading zero, no answer
            return;
        }

        // decoding first char
        int firstDigit = str.charAt(0) - '0'; // 1
        char cChar = (char)('a' + (firstDigit - 1)); // 'a'

        String remString = str.substring(1); // 234

        printEncodings(remString, asf + cChar);

        // decoding first two characters
        if(str.length() >= 2){
            String first2Characters = str.substring(0,2);

            int firstTwoDigits = Integer.parseInt(first2Characters);

            if(firstTwoDigits <= 26){
                cChar = (char)('a' + (firstTwoDigits - 1));
                remString = str.substring(2);
                printEncodings(remString, asf + cChar);
            }
        }
    }

    // printPermutations
    public static void printPermutations(String str, String asf){
        if(str.length() == 0){
            System.out.println(asf);
            return;
        }


        for(int i=0; i<str.length(); i++){
            char ch = str.charAt(i);

            String leftRemaining = str.substring(0,i);
            String rightRemaining = str.substring(i+1);

            String remainingString = leftRemaining + rightRemaining;

            printPermutations(remainingString, asf + ch);
        }
    }

    // 0,0,n,m,matrix
    public static int maxPathSum(int row, int col,int n, int m, int[][] matrix){
        if(row == n-1 && col == m-1){
            return matrix[row][col];
        }

        if(row >= n || col >= m){
            return Integer.MIN_VALUE;
        }
        
        int rightPathSum = maxPathSum(row,col+1,n,m,matrix);
        int downPathSum = maxPathSum(row+1,col,n,m,matrix);

        int maxSum = Math.max(rightPathSum, downPathSum) + matrix[row][col];

        return maxSum;
    }

    






























    public static void main(String[] args){
        // printAllMazePathsWithJumps(0,0,2,2, "");
        // printEncodings("303","");
        printPermutations("abc","");
    }
}